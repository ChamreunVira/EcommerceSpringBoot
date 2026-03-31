package com.kh.vira_dev.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity{

    @Column(name = "slug" , unique = true, nullable = false , length = 30)
    private String slug;

    @Column(name = "name" , unique = true, nullable = false , length = 50)
    private String name;

    @Column(name = "description" , nullable = false , length = 100)
    private String description;

    @Column(name = "tags" , nullable = false , length = 15)
    private List<String> tags = new ArrayList<>();

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Product> products;

}
