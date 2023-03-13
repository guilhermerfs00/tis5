package com.puc.ticketin.api.controller;

import com.puc.ticketin.api.request.AuthenticationRequest;
import com.puc.ticketin.api.request.UserRequest;
import com.puc.ticketin.api.response.EventResponse;
import com.puc.ticketin.api.response.UserResponse;
import com.puc.ticketin.domain.bo.HttpError;
import com.puc.ticketin.domain.bo.TockenBO;
import com.puc.ticketin.domain.bo.UserBO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Tag(name = "Usuario", description = "Endpoints relativos ao Usuario")
@Validated
@Valid
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface IUserController {

    @Operation(summary = "Login", description = "Buscar as credenciais dos usuario no sistema",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Usuario encontrado sucesso.",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido."),
                    @ApiResponse(responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = HttpError.class)))
            })
    @PostMapping("/login")
    ResponseEntity<Mono<TockenBO>> login(@Valid @RequestBody Mono<AuthenticationRequest> authRequest);

    @Operation(summary = "Criar um novo usuário", description = "Adicionar um novo usuário no sistema",
            responses = {@ApiResponse(responseCode = "201",
                    description = "Usuario criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido."),
                    @ApiResponse(responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = HttpError.class)))
            })
    @PostMapping("/create")
    ResponseEntity<Mono<UserResponse>> createUser(@Valid @RequestBody Mono<UserRequest> user);

    @Operation(summary = "Consulta todos usuário", description = "Consulta uma lista de usuarios",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Usuarios encontrados com sucesso.",
                    content = @Content(schema = @Schema(implementation = UserBO.class))),
                    @ApiResponse(responseCode = "204",
                            description = "Nenhuma usuario encontrado."),
                    @ApiResponse(responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido."),
                    @ApiResponse(responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = HttpError.class)))
            })
    @GetMapping
    ResponseEntity<Flux<UserResponse>> findAllUsers();

    @Operation(summary = "Consulta usuário", description = "Consulta dados do usuario logado",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Usuario encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "204",
                            description = "Nenhuma usuario encontrado."),
                    @ApiResponse(responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido."),
                    @ApiResponse(responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = HttpError.class)))
            })
    @GetMapping("/principal")
    Mono<ResponseEntity> currentUser(@AuthenticationPrincipal Mono<UserDetails> principal);


}
