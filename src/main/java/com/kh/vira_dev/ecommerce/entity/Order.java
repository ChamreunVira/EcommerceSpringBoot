package com.kh.vira_dev.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at" , nullable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    private List<OrderLine> orderLines;
}
