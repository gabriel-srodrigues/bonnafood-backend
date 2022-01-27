package br.com.bonnafood.app.users.domain.model;

import br.com.bonnafood.app.users.domain.enums.EnumRoleType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "user")
@Where(clause = "active = true")
@EntityListeners(AuditingEntityListener.class)
public class BonnaUser {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    private String name;

    private String email;

    private String phone;

    private String password;

    private String avatar;

    private OffsetDateTime lastLogin;

    @Embedded
    private Location location;

    @Embedded
    private BankAccount bankAccount;

    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private EnumRoleType role;

    private boolean active;

    @ManyToMany
    @JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    @CreatedDate
    private OffsetDateTime createdAt;

    @LastModifiedDate
    private OffsetDateTime updatedAt;

    public void activate() {
        this.active = true;
    }

    public boolean isNewUser(){
        return this.id == null;
    }

    public void updateLastLogin() {
        this.setLastLogin(OffsetDateTime.now());
    }
}
