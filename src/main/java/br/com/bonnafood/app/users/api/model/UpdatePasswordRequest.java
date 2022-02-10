package br.com.bonnafood.app.users.api.model;

import br.com.bonnafood.app.users.api.validation.Password;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePasswordRequest {
    @NotBlank
    @JsonProperty("old_password")
    @Schema(example = "PtG%25asgLG")
    private String oldPassword;

    @NotBlank
    @Password
    @JsonProperty("new_password")
    @Schema(example = "GMdasa!@sdgs")
    private String newPassword;
}
