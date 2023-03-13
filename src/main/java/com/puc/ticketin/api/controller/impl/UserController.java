package com.puc.ticketin.api.controller.impl;

import com.puc.ticketin.api.controller.IUserController;
import com.puc.ticketin.api.request.AuthenticationRequest;
import com.puc.ticketin.api.request.UserRequest;
import com.puc.ticketin.api.response.UserResponse;
import com.puc.ticketin.domain.bo.TockenBO;
import com.puc.ticketin.domain.bo.UserBO;
import com.puc.ticketin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController implements IUserController {
    private final UserService service;

    @Override
    public ResponseEntity<Mono<TockenBO>> login(Mono<AuthenticationRequest> authRequest) {
        return ResponseEntity.ok().body(service.login(authRequest));
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> createUser(Mono<UserRequest> userRequest) {
        return ResponseEntity.ok().body(service.createUser(userRequest));
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAllUsers() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Override
    public Mono<ResponseEntity> currentUser(@AuthenticationPrincipal Mono<UserDetails> principal) {
        return Mono.just(service.currentUser(principal))
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK));
    }

}
