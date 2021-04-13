package com.leogersen.alfornoapi.domain.order;

import com.leogersen.alfornoapi.domain.restaurant.MenuItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "order_item")
public class OrderItem implements Serializable {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private OrderItemPK id;

    @NotNull
    @ManyToOne
    private MenuItem menuItem;

    @NotNull
    private Integer quantity;

    @NotEmpty
    private String obs;

    @NotNull
    private BigDecimal price;


    public MenuItem getMenuItemsInfo() {
        return menuItem;
    }

    public int getOrderItemCommand() {
        return id.getCommand();
    }


}
