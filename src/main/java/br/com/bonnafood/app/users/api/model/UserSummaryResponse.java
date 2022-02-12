package br.com.bonnafood.app.users.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class UserSummaryResponse extends RepresentationModel<UserSummaryResponse> {
    private String id;
    private String name;
    private String email;
    private String phone;
}
