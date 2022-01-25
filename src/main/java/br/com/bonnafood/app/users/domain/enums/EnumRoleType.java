package br.com.bonnafood.app.users.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumRoleType {
    USER(0, "user"),
    EDITOR(1, "editor"),
    MANAGER(2, "editor");

    private final int order;
    private final String name;
}
