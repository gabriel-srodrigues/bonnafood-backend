package br.com.bonnafood.app.users.domain.model;

import br.com.bonnafood.app.users.domain.enums.BankAccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    @Column(name = "bank_account_bank_code")
    private String bankCode;

    @Column(name = "bank_account_agency")
    private String agency;

    @Column(name = "bank_account_number")
    private String number;

    @Column(name = "bank_account_digit")
    private String digit;

    @Column(name = "bank_account_type")
    @Enumerated(EnumType.STRING)
    private BankAccountType type;
}
