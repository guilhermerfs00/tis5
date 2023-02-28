package com.puc.ticketin.api.controller;

import com.puc.ticketin.api.request.AuthenticationRequest;
import com.puc.ticketin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController implements IUserController {
    private final UserService service;

    @Override
    public Mono<ResponseEntity> login(Mono<AuthenticationRequest> authRequest) {
        return Mono.just(service.login(authRequest))
                .map(jwt -> {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.add(AUTHORIZATION, "Bearer " + jwt);
                    return new ResponseEntity<>(Map.of("access_token", jwt), httpHeaders, HttpStatus.OK);
                });
    }

    @Override
    public Mono<ResponseEntity> findByUsername(String username) {
        return Mono.just(service.findByUsername(username))
                .map(userBOMono -> new ResponseEntity<>(userBOMono, HttpStatus.OK));
    }

    @Override
    public Mono<ResponseEntity> currentUser(@AuthenticationPrincipal Mono<UserDetails> principal) {
        return Mono.just(service.currentUser(principal))
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK));
    }

}
