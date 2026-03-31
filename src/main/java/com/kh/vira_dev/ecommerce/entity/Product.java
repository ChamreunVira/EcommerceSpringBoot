package com.kh.vira_dev.ecommerce.entity;

import com.kh.vira_dev.ecommerce.constant.DiscountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "title" , nullable = false , length = 50)
    private String title;

    @Column(name = "picture"  , nullable = false , length = 50)
    private String picture;

    @Column(name = "summary" , nullable = false , length = 100)
    private String summary;

    @Column(name = "price" , nullable = false)
    private float price;

    @Column(name = "discount_type" , nullable = false)
    private DiscountType discountType;

    @Column(name = "discount_value" , nullable = false)
    private float discountValue;

    @Column(name = "tags" , nullable = false , length = 15)
    private List<String> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id" , referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    private List<OrderLine> orderLines;

}
