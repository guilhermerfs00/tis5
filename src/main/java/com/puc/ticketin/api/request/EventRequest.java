package com.puc.ticketin.api.request;

import com.puc.ticketin.domain.helper.CpfCnpj;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
@Builder
@Schema(description = "Representa a solicitação para Simulação da Proposta")
public class EventRequest {

    @NotNull
    @Schema(description = "Nome do Evento", example = "Planeta Brasil", required = true)
    String name;

    LocalDateTime createdDate;

}
