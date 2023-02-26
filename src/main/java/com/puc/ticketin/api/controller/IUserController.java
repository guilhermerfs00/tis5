package com.puc.ticketin.api.controller;//package com.example.demo.api.controller;

import com.puc.ticketin.api.request.AuthenticationRequest;
import com.puc.ticketin.domain.bo.UserBO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Tiketin")
@RequestMapping(value = "tiketin", produces = APPLICATION_JSON_VALUE)
public interface IUserController {

    @Operation(
            summary = "Buscar um usuaro registrado",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Usuario encontrado com sucesso.")
            }
    )
    @PostMapping("/login")
    Mono<ResponseEntity> authenticate(@Valid @RequestBody Mono<AuthenticationRequest> authRequest);


    @Operation(
            summary = "Buscar um usuaro registrado",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Usuario encontrado com sucesso.")
            }
    )
    @GetMapping("/users/{username}")
    Mono<UserBO> findByUsername(@PathVariable String username);

}
