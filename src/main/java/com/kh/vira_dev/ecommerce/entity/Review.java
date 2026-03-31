package com.kh.vira_dev.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating" , nullable = false)
    private int rating;

    @Column(name = "comment" , nullable = true , length = 50)
    private String comment;

    @CreationTimestamp
    @Column(name = "created_at" , nullable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;


}
