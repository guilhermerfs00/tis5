package com.puc.ticketin.api.response;//package com.example.demo.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

    private String name;
    private LocalDateTime createdDate;


}
