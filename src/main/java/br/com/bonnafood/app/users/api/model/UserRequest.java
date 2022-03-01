package br.com.bonnafood.app.users.api.model;

import br.com.bonnafood.app.users.api.validation.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    @Schema(example = "Peter Parker", description = "Nome completo")
    private String name;
    @Email
    @NotBlank
    @Schema(example = "peter.parker@bonnafood.com.br", description = "E-mail do usuário")
    private String email;
    @Phone
    @NotBlank
    @Schema(example = "(99) 99999-9999", description = "Telefone do usuário")
    private String phone;
}
