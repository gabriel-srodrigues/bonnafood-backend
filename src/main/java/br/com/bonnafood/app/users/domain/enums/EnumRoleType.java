package br.com.bonnafood.app.users.domain.enums;

import br.com.bonnasys.domain.enums.EnumListable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumRoleType implements EnumListable<EnumRoleType> {
    USER("option_enum_role_type_user", 0, "user"),
    EDITOR("option_enum_role_type_editor", 1, "editor"),
    MANAGER("option_enum_role_type_manager", 2, "editor");

    private final String i18nKey;
    private final int order;
    private final String name;

    @Override
    public String getI18nKey() {
        return i18nKey;
    }
}
