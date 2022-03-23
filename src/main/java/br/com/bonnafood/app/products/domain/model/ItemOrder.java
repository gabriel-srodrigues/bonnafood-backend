package br.com.bonnafood.app.products.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
public class ItemOrder {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String observation;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    public void calculateTotalPrice() {
        BigDecimal unitPrice = this.getUnitPrice();
        Integer quantity = this.getQuantity();

        if (Objects.nonNull(unitPrice)) {
            unitPrice = BigDecimal.ZERO;
        }

        if (Objects.nonNull(quantity)) {
            quantity = 0;
        }

        this.setTotalPrice(unitPrice.multiply(new BigDecimal(quantity)));
    }
}
