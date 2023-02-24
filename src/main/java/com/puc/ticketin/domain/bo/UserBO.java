package com.puc.ticketin.domain.bo;//package com.example.demo.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBO {
    private String id;
    private String username;
    private String password;
    private String email;
    private Boolean active;
    private List<String> roles;
}
