package com.puc.ticketin.service;//package com.example.demo.service;

import com.puc.ticketin.api.request.AuthenticationRequest;
import com.puc.ticketin.domain.bo.UserBO;
import com.puc.ticketin.domain.mapper.UserMapper;
import com.puc.ticketin.repository.ReactiveUserRepository;
import com.puc.ticketin.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ReactiveUserRepository reactiveUserRepository;
    private static final UserMapper mapper = UserMapper.INSTANCE;
    private final JwtTokenProvider tokenProvider;
    private final ReactiveAuthenticationManager authenticationManager;

    public Mono<String> login(Mono<AuthenticationRequest> authRequest) {
        return authRequest
                .flatMap(login -> authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()))
                        .map(this.tokenProvider::createToken));
    }

    public Mono<UserBO> findByUsername(String username) {
        return Mono.just(username)
                .flatMap(reactiveUserRepository::findByUsername)
                .switchIfEmpty(Mono.error(new RuntimeException("UserNotFoud")))
                .map(mapper::entityToBo)
                .doOnSuccess(current -> log.info("Consulta, usuario <{}> encontrado", username));
    }

    public Mono<Map<String, Object>> currentUser(Mono<UserDetails> principal) {
        return principal.map(user -> Map.of(
                        "name", user.getUsername(),
                        "roles", AuthorityUtils.authorityListToSet(user.getAuthorities())
                )
        );
    }

}
