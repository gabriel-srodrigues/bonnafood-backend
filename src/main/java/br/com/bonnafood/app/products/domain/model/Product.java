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
public class Product extends Auditable<User> {

    private String name;

    @Lob
    private String description;

    private double price;

    private boolean available;

    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

}
