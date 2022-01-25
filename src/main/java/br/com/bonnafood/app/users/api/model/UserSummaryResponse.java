package br.com.bonnafood.app.users.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummaryResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
}
