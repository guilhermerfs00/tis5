package com.puc.ticketin.api.controller;

import com.puc.ticketin.api.request.AuthenticationRequest;
import com.puc.ticketin.domain.bo.UserBO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Tag(name = "Usuario", description = "Endpoints relativos ao Usuario")
@Validated
@Valid
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface IUserController {

    @Operation(
            summary = "Buscar um usuaro registrado",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Usuario encontrado com sucesso.")
            }
    )
    @PostMapping("/login")
    Mono<ResponseEntity> login(@Valid @RequestBody Mono<AuthenticationRequest> authRequest);


    @Operation(
            summary = "Buscar um usuaro registrado",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Usuario encontrado com sucesso.")
            }
    )
    @GetMapping("/{username}")
    Mono<ResponseEntity> findByUsername(@PathVariable String username);

    @Operation(
            summary = "Buscar o dados de um usuario",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Usuario encontrado com sucesso.")
            }
    )
    @GetMapping("/principal")
    Mono<ResponseEntity> currentUser(@AuthenticationPrincipal Mono<UserDetails> principal);


}
