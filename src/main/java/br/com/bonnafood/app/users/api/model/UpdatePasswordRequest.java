package br.com.bonnafood.app.users.api.model;

import br.com.bonnafood.app.users.api.validation.Password;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePasswordRequest {
    @NotBlank
    private String oldPassword;

    @NotBlank
    @Password
    private String newPassword;
}
