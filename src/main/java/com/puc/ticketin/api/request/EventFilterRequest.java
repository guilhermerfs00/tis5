package com.puc.ticketin.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
@Builder
@Schema(description = "Evento")
public class EventFilterRequest {

    @Schema(description = "Nome do Evento", example = "Planeta Brasil", required = false)
    String name;
    LocalDateTime createdDate;

}
