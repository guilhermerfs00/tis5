package com.puc.ticketin.service;

import com.puc.ticketin.api.request.AuthenticationRequest;
import com.puc.ticketin.api.request.UserRequest;
import com.puc.ticketin.api.response.UserResponse;
import com.puc.ticketin.domain.bo.TockenBO;
import com.puc.ticketin.domain.bo.UserBO;
import com.puc.ticketin.domain.entity.User;
import com.puc.ticketin.domain.enums.RoleEnum;
import com.puc.ticketin.domain.mapper.UserMapper;
import com.puc.ticketin.repository.ReactiveUserRepository;
import com.puc.ticketin.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ReactiveUserRepository reactiveUserRepository;
    private static final UserMapper mapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final ReactiveAuthenticationManager authenticationManager;

    public Mono<TockenBO> login(Mono<AuthenticationRequest> authRequest) {
        return authRequest
                .flatMap(login -> this.authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                login.getEmail(), login.getPassword()))
                        .map(this.tokenProvider::createToken))
                .map(jwt -> {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
                    Map tokenBody = Map.of("access_token", jwt);
                    return TockenBO.builder().accesToken(tokenBody).headers(httpHeaders).build();
                });
    }

    public Mono<UserResponse> createUser(Mono<UserRequest> userRequest) {
        return userRequest
                .map(this::userSecurityConfig)
                .flatMap(reactiveUserRepository::save)
                .map(mapper::entityToResponse)
                .doOnSuccess(user
                        -> log.info("Registro, usuario <{}> salvo com sucesso", user.getName()));
    }


    public Mono<UserBO> findByEmail(String username) {
        return Mono.just(username)
                .flatMap(reactiveUserRepository::findByEmail)
                .switchIfEmpty(Mono.error(new RuntimeException("Email not found")))
                .map(mapper::entityToBo)
                .doOnSuccess(current -> log.info("Consulta, usuario <{}> encontrado", username));
    }

    public Flux<UserResponse> findAll() {
        return reactiveUserRepository.findAll()
                .switchIfEmpty(Mono.error(new RuntimeException("UserNotFoud")))
                .map(mapper::entityToBo)
                .map(mapper::boToResonse);
    }

    public Mono<Map<String, Object>> currentUser(Mono<UserDetails> principal) {
        return principal.map(user -> Map.of(
                        "name", user.getUsername(),
                        "roles", AuthorityUtils.authorityListToSet(user.getAuthorities())
                )
        );
    }

    private User userSecurityConfig(UserRequest userBO) {
        return User.builder()
                .name(userBO.getName())
                .password(passwordEncoder.encode(userBO.getPassword()))
                .email(userBO.getEmail())
                .active(userBO.getActive())
                .roles(userBO.getRoles().stream().map(RoleEnum::getValue).collect(Collectors.toList()))
                .build();
    }

}
