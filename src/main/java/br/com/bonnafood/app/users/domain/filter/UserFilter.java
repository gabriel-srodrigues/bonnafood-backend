package br.com.bonnafood.app.users.domain.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {
    private String term;
    private String name;
    private String email;
}
