package com.puc.ticketin.api.response;

import com.puc.ticketin.domain.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
@Builder
@Schema(description = "Usuario")
public class UserResponse {

    @Id
    String id;
    @NotNull
    @Schema(description = "Nome do usuário", example = "Guilherme Roberto", required = true)
    String name;
    @Email
    @NotNull
    @NotEmpty
    @Schema(description = "Email do usuário", example = "guilherme@gmail.com", required = true)
    String email;

    @Builder.Default()
    @Schema(description = "Usuario ativo : default: true", example = "true")
    Boolean active = true;

    @Builder.Default()
    @Schema(description = "Perfil de usuário")
    List<RoleEnum> roles = new ArrayList<>();

}
