package com.kh.vira_dev.ecommerceapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "product_name" , nullable = false , length = 30)
    private String productName;

    @Column(name = "product_image" , length = 50)
    private String productImage;

    @Column(name = "unit_price" , nullable = false , precision = 10 , scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "discount_rate" , nullable = false , precision = 10 , scale = 2)
    private float discountRate;

    @Column(name = "final_price" , nullable = false , precision = 10 , scale = 2)
    private float finalPrice;

    @Column(name = "qauntity" , nullable = false)
    private int quantity;

    @Column(name = "subtotal" , nullable = false , precision = 10 , scale = 2)
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id" , referencedColumnName = "id")
    private Order order;

}
