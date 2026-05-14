package com.kh.vira_dev.ecommerceapi.entity;

import com.kh.vira_dev.ecommerceapi.enums.Permission;
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
        
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.name()));
            for (Permission permission : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.name()));
            }
        });
        
        return authorities;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private RefreshToken refreshToken;

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Cart cart;

}
