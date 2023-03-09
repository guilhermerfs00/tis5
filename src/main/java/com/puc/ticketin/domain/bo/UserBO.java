package com.puc.ticketin.domain.bo;//package com.example.demo.domain.bo;

import com.puc.ticketin.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBO {
    private String id;
    private String password;
    private String email;
    private Boolean active;
    private List<RoleEnum> roles;
}
