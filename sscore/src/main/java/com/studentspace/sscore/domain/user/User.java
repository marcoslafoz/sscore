package com.studentspace.sscore.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "public")
public class User implements UserDetails {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(name = "email")
    private String email;
    @Column
    private String password;
    @ManyToOne
    @JoinColumn(name="id_avatar")
    private Avatar avatar;
    @Column(name = "birthday")
    private Date birthday;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
