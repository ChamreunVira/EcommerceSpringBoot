package com.kh.vira_dev.ecommerce.entity;

import com.kh.vira_dev.ecommerce.constant.Hasher;
import com.kh.vira_dev.ecommerce.constant.Provider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_credential")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private Provider provider;

    @Column(name = "provider_key")
    private String providerKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "hasher")
    private Hasher hasher;

    @Column(name = "password_hash" , length = 255 , nullable = false)
    private String passwordHash;

    @Column(name = "password_salt")
    private String passwordSalt;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;

}
