package com.puc.ticketin.api.controller;

import com.puc.ticketin.api.request.EventFilterRequest;
import com.puc.ticketin.api.request.EventRequest;
import com.puc.ticketin.api.response.EventResponse;
import com.puc.ticketin.api.response.PageResponse;
import com.puc.ticketin.domain.bo.HttpError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Users")
@RestController()
@RequestMapping(value = "/event")
@Validated
public interface IEventController {

//    @Operation(summary = "Obtenção de um Evento.", description = "Obtém um evento paginado por filro.", responses = {
//            @ApiResponse(responseCode = "200",
//                    description = "Operação realizada com sucesso.",
//                    content = @Content(schema = @Schema(implementation = EventResponse.class))),
//            @ApiResponse(responseCode = "400",
//                    description = "Requisição possui algum valor faltante ou inválido."),
//            @ApiResponse(responseCode = "422",
//                    description = "Foi encontrada alguma condição inválida.",
//                    content = @Content(schema = @Schema(implementation = HttpError.class))),
//            @ApiResponse(responseCode = "502",
//                    description = "Ocorreu um erro inesperado ao contactar com algum recurso externo.",
//                    content = @Content(schema = @Schema(implementation = HttpError.class)))})
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
//    Mono<EventResponse> getEvent(
//            @Schema(description = "Event Request", example = "MRV", required = true)
//            @RequestParam @NotNull EventRequest eventRequest,
//            @Schema(description = "CPF or CNPJ do Cedente", example = "38990808000136", required = true)
//            @RequestHeader(value = "x-assignor-cpfcnpj") @NotNull String assignorCpfCnpj
//    );


    @Operation(summary = "Consulta por filtro", description = "Consulta eventos paginados de acordo com o filtro informado",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Eventos encontrados com sucesso.",
                    content = @Content(schema = @Schema(implementation = EventResponse.class, oneOf = {PageRequest.class}))),
                    @ApiResponse(responseCode = "204",
                            description = "Nenhuma evento encontrado."),
                    @ApiResponse(responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido."),
                    @ApiResponse(responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = HttpError.class)))
            })
    @GetMapping
    @ResponseStatus(OK)
    @PageableAsQueryParam
    Mono<PageResponse<EventResponse>> listPaginated(@Parameter(hidden = true) Pageable page, EventFilterRequest filterRequest);
}
