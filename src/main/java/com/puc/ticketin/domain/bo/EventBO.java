package com.puc.ticketin.domain.bo;//package com.example.demo.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventBO {
    private String id;
    private String name;
    private LocalDateTime createdDate;
}
