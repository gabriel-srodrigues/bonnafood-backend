package br.com.bonnafood.app.users.domain.enums;

import br.com.bonnasys.common.domain.enums.EnumListable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BankAccountType implements EnumListable<BankAccountType> {
    SAVING("option_bank_account_type_saving"),
    CHECKING("option_bank_account_type_checking");

    private final String i18nKey;

    @Override
    public String getI18nKey() {
        return i18nKey;
    }
}
