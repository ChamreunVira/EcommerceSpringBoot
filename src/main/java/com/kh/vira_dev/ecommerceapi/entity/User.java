package com.kh.vira_dev.ecommerceapi.entity;

import com.kh.vira_dev.ecommerceapi.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fullName" , nullable = false , length = 50)
    private String fullName;

    @Column(name = "email" , nullable = false , unique = true , length = 50)
    private String email;

    @Column(name = "password" , nullable = false , length = 255)
    private String password;

    private boolean status = false;

    @Column(name = "roles" , nullable = false , length = 50)
    private Set<Role> roles = new HashSet<>();

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> {
                    String defaultRole = role.name();
                    if(!defaultRole.startsWith("ROLE_")) {
                        defaultRole = "ROLE_" + defaultRole;
                    }
                    return new SimpleGrantedAuthority(defaultRole);
                })
                .collect(Collectors.toSet());
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private RefreshToken refreshToken;

}
