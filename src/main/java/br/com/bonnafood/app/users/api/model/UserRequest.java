package br.com.bonnafood.app.users.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
    @NotBlank
    @Schema(example = "Peter Parker", description = "Nome completo")
    private String name;
    @NotBlank
    @Email
    @Schema(example = "peter.parker@bonnafood.com.br", description = "E-mail do usuário")
    private String email;
    @NotBlank
    @Schema(example = "(99) 99999-9999", description = "Telefone do usuário")
    private String phone;
}
