package com.kh.vira_dev.ecommerce.entity;

import com.kh.vira_dev.ecommerce.entity.composite.CartItemId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_cart_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {


    @EmbeddedId
    private CartItemId cartItemId;

    @Column(name = "price" , nullable = false)
    private Float price;

    @Column(name = "quantity" , nullable = false)
    private int quantity;

    @Column(name = "created_at" , nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id" , referencedColumnName = "id")
    private Cart cart;

}
