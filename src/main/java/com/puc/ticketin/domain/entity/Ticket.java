package com.puc.ticketin.domain.entity;//package com.example.demo.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
@Builder
@Schema(description = "Representa a solicitação para Simulação da Proposta")
public class Ticket implements Serializable {

    @NotNull
    @Schema(description = "Valor do ticket", example = "19.20", required = true)
    BigDecimal value;
}
