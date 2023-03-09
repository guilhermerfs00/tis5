package com.puc.ticketin.domain.entity;//package com.example.demo.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
@Builder
@Schema(description = "Representa a solicitação para Simulação da Proposta")
public class Order implements Serializable {

    @Id
    String id;

    Event event;
    Ticket ticket;
    String status;
    String paymentMethod;



}
