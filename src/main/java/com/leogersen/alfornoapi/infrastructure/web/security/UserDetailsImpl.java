package com.leogersen.alfornoapi.infrastructure.web.security;

import com.leogersen.alfornoapi.domain.client.Client;
import com.leogersen.alfornoapi.domain.restaurant.Restaurant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private String email;
    private String name;
    private String password;



    public UserDetailsImpl(Client user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.password= user.getPassword();

    }
    public UserDetailsImpl(Restaurant user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.password= user.getPassword();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.NO_AUTHORITIES;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

