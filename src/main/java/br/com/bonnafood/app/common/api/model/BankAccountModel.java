package br.com.bonnafood.app.common.api.model;

import br.com.bonnafood.app.users.domain.enums.BankAccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountModel {
    private String bankCode;
    private String agency;
    private String number;
    private String digit;
    private BankAccountType type;
}
