package br.com.bonnafood.app.products.domain.model;

import br.com.bonnafood.app.common.jpa.Auditable;
import br.com.bonnafood.app.users.domain.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends Auditable<User> {

    private String name;

    @Lob
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

}