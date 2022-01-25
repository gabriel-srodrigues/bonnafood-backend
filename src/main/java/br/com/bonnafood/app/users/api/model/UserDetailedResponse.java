package br.com.bonnafood.app.users.api.model;

import br.com.bonnafood.app.users.domain.enums.EnumRoleType;
import br.com.bonnafood.app.users.domain.model.Group;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
public class UserDetailedResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
    private EnumRoleType role;
    private boolean active;
    private Set<Group> groups;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
