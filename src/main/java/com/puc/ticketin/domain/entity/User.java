package com.puc.ticketin.domain.entity;//package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puc.ticketin.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

    @Id
    private String id;

    private String name;

    @JsonIgnore
    private String password;

    @Email
    private String email;

    @Builder.Default()
    private boolean active = true;

    @Builder.Default()
    private List<RoleEnum> roles = new ArrayList<>();

}
