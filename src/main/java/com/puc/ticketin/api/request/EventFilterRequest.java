package com.puc.ticketin.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
@Builder
@Schema(description = "Representa a solicitação para Simulação da Proposta")
public class EventFilterRequest {

    @NotNull
    @Schema(description = "Nome do Evento", example = "Planeta Brasil", required = true)
    String name;

    LocalDateTime createdDate;

}
