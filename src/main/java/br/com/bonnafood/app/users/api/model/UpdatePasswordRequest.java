package br.com.bonnafood.app.users.api.model;

import br.com.bonnafood.app.users.api.validation.Password;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePasswordRequest {
    @NotBlank
    @Schema(example = "PtG%25asgLG")
    private String oldPassword;

    @NotBlank
    @Password
    @Schema(example = "GMdasa!@sdgs")
    private String newPassword;
}
