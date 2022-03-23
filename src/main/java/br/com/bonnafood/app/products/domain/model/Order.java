package br.com.bonnafood.app.products.domain.model;

import br.com.bonnafood.app.common.jpa.Auditable;
import br.com.bonnafood.app.products.domain.enums.EnumOrderStatus;
import br.com.bonnafood.app.users.domain.model.Location;
import br.com.bonnafood.app.users.domain.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class Order extends Auditable<User> {
    private BigDecimal subtotal;

    private BigDecimal shippingFee;

    private BigDecimal totalValue;

    @Enumerated(EnumType.STRING)
    private EnumOrderStatus status = EnumOrderStatus.CREATED;

    @OneToMany(mappedBy = "order")
    private List<ItemOrder> itemOrderList;

    @Embedded
    private Location location;


}
