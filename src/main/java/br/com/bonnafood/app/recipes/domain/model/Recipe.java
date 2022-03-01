package br.com.bonnafood.app.recipes.domain.model;

import br.com.bonnafood.app.common.jpa.Auditable;
import br.com.bonnafood.app.users.domain.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Where(clause = "deleted = false")
public class Recipe extends Auditable<User> {

    private String title;

    private String body;

    private Duration cookingTime;

    private String image;

    private String video;

    private boolean deleted;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "tag", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name="name")
    private List<String> tags = new ArrayList<>();

    public boolean isNew() {
        return getId() == null;
    }
}
