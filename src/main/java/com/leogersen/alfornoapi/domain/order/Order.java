package com.leogersen.alfornoapi.domain.order;

import com.leogersen.alfornoapi.domain.client.Client;
import com.leogersen.alfornoapi.domain.restaurant.Restaurant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@Table(name = "orders")
@EntityListeners(OrderListener.class)
public class Order implements Serializable {

    public enum Status {
        Production(1, "Em produção", false),
        Delivery(2, "Saiu para Entrega", false),
        Completed(3, "Conluido", true),
        Cancelled(4, "Pedido Cancelado", false);


        int sequence;
        String description;
        boolean lastOne;

        public int getSequence() {
            return sequence;
        }

        public String getDescription() {
            return description;
        }

        public boolean isLastOne() {
            return lastOne;
        }

        public static Status fromSequence(int sequence) {
            for(Status status : Status.values()){
                if(status.getSequence() == sequence){
                    return status;
                }
            }
            return null;
        }

        Status(int sequence, String description, boolean lastOne) {
            this.description = description;
            this.sequence= sequence;
            this.lastOne= lastOne;

        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Status status;

    @NotNull
    @ManyToOne
    private Client client;

    @NotNull
    @ManyToOne
    private Restaurant restaurant;

    @NotNull
    private BigDecimal subTotal;

    @NotNull
    @Column(name = "delivery_tax")
    private BigDecimal deliveryTax;

    @NotNull
    private BigDecimal total;

    @OneToMany(mappedBy = "id.order", fetch = FetchType.EAGER)
    private Set<OrderItem> items;

    public String getFormattedId() {
        return String.format("#%04d", id);
    }

    public void defineNextStatus() {
        int sequence = status.getSequence();

        Status newStatus = Status.fromSequence(sequence + 1);

        if (newStatus != null) {
            this.status = newStatus;
        }


    }
    public int getClientId() {
        return client.getId();
        
    }

    public Set<OrderItem> getOrderItemsList() {
        return items;
    }

    public String getRestaurantName() {
        
        return restaurant.getName();

    }

}
