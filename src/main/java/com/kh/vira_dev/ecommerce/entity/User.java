package com.kh.vira_dev.ecommerce.entity;

import com.kh.vira_dev.ecommerce.constant.Locale;
import com.kh.vira_dev.ecommerce.constant.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Column(name = "slug" , unique = true)
    private String slug;

    @Column(name = "email" , nullable = false , length = 50)
    private String email;

    @Column(name = "phone" , nullable = false , length = 20)
    private String phone;

    @Column(name = "name" , nullable = false , length = 50)
    private String name;

    @Column(name = "avatar" , nullable = false , length = 50)
    private String avatar;

    @Column(name = "local" , length = 20)
    private Locale locale;

    @Column(name = "last_login" , nullable = false)
    private Instant lastLogin;

    @Column(name = "email_validated")
    private Instant emailValidated;

    @Column(name = "phone_validated")
    private Instant phoneValidated;

    @Column(name = "bio", length = 100)
    private String bio;

    @Column(name = "company" , nullable = false , length = 50)
    private String company;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role" , nullable = false , length = 30)
    private List<Role> role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER , orphanRemoval = true)
    private List<Credential> credentials;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, fetch = FetchType.LAZY , orphanRemoval = true)
    private List<SocialProfile> socialProfiles;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, fetch = FetchType.LAZY , orphanRemoval = true)
    private List<Review> reviews;

    @Override
    @NullMarked
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream()
                .map(role -> {
                    String defaultRole = role.name();
                    if(!defaultRole.startsWith("ROLE_")) {
                        defaultRole = "ROLE_" + defaultRole;
                    }
                    return new SimpleGrantedAuthority(defaultRole);
                })
                .toList();
    }

    @Override
    @NullMarked
    public @Nullable String getPassword() {
        return null;
    }

    @Override
    @NullMarked
    public String getUsername() {
        return email;
    }

    @Override
    @NullMarked
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @NullMarked
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @NullMarked
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @NullMarked
    public boolean isEnabled() {
        return true;
    }
}
