package com.kh.vira_dev.ecommerce.entity;

import com.kh.vira_dev.ecommerce.entity.composite.OrderLineId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_order_line")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {

    @EmbeddedId
    private OrderLineId  orderLineId;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id" , referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    private Product product;

    @Column(name = "price" , nullable = false)
    private Float price;

    @Column(name = "quantity" , nullable = false)
    private int quantity;

}
