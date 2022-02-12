package br.com.bonnafood.app.users.api.model;

import br.com.bonnafood.app.common.api.model.BankAccountModel;
import br.com.bonnafood.app.common.api.model.LocationModel;
import br.com.bonnafood.app.users.domain.enums.EnumRoleType;
import br.com.bonnafood.app.users.domain.model.BankAccount;
import br.com.bonnafood.app.users.domain.model.Location;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
public class UserDetailedResponse extends RepresentationModel<UserDetailedResponse> {
    private String id;
    private String name;
    private String email;
    private String phone;
    private EnumRoleType role;
    private boolean active;
    private String avatar;
    private OffsetDateTime lastLogin;
    private LocationModel location;
    private BankAccountModel bankAccount;
    private LocalDate birthdate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
