package com.puc.ticketin.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puc.ticketin.domain.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
@Builder
@Schema(description = "Usuario")
public class UserRequest {

    @NotNull
    @Schema(description = "Nome do usuário", example = "Guilherme Roberto", required = true)
    String name;
    @Email
    @NotNull
    @NotEmpty
    @Schema(description = "Email do usuário", example = "guilherme@gmail.com", required = true)
    String email;

    @NotNull
    @NotEmpty
    @Schema(description = "Senha do usuário", example = "123", required = true)
    String password;


    @Builder.Default()
    @Schema(description = "Usuario ativo : default: true", example = "true")
    Boolean active = true;

    @Builder.Default()
    @Schema(description = "Perfil de usuário")
    List<RoleEnum> roles = new ArrayList<>();

}
