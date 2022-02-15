package br.com.bonnafood.app.users.api.model;

import br.com.bonnafood.app.users.api.validation.Password;
import br.com.bonnafood.app.users.api.validation.Phone;
import br.com.bonnafood.app.users.domain.enums.EnumRoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserPasswordRequest {
    @NotBlank
    @Schema(example = "Peter Parker", description = "Nome completo")
    private String name;
    @NotBlank
    @Schema(example = "peter.parker@bonnafood.com.br", description = "E-mail do usuário")
    private String email;
    @Phone
    @NotBlank
    @Schema(example = "(99) 99999-9999", description = "Telefone do usuário")
    private String phone;
    @NotBlank
    @Password
    @Schema(example = "A&bt0TC14D", description = "Senha com no min 8 caracteres, uma letra maiúscula, uma letra minúscula e um símbolo")
    private String password;
    @NotNull
    @Schema(example = "USER", description = "Tipo de usuário")
    private EnumRoleType role;

    private LocalDate birthdate;
}
