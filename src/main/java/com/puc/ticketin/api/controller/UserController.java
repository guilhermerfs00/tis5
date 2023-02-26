package com.puc.ticketin.api.controller;//package com.example.demo.api.controller;

import com.puc.ticketin.api.request.AuthenticationRequest;
import com.puc.ticketin.domain.mapper.UserMapper;
import com.puc.ticketin.repository.UserRepository;
import com.puc.ticketin.security.JwtTokenProvider;
import com.puc.ticketin.service.UserService;
import com.puc.ticketin.domain.bo.UserBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController implements IUserController {
    private final UserService service;

    @Override
    public Mono<ResponseEntity> authenticate(Mono<AuthenticationRequest> authRequest) {
        return Mono.just(service.login(authRequest))
                .map(jwt -> new ResponseEntity<>(jwt, HttpStatus.OK));
    }

    @Override
    public Mono<UserBO> findByUsername(String username) {
        return service.findByUsername(username);
    }


}
