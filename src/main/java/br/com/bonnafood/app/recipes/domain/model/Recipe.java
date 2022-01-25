package br.com.bonnafood.app.recipes.domain.model;

import br.com.bonnafood.app.users.domain.model.BonnaUser;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Recipe {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    private String title;

    private String body;

    private LocalTime cookingTime;

    @CreatedBy
    @ManyToOne
    private BonnaUser createdBy;

    @CreatedDate
    private OffsetDateTime createdAt;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private BonnaUser updatedBy;

    @LastModifiedDate
    private OffsetDateTime updatedAt;

    private String image;

    private String video;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "tag", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name="name")
    private List<String> tags = new ArrayList<>();

}
