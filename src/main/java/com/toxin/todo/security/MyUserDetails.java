package com.toxin.todo.security;

import com.toxin.todo.entity.User;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyUserDetails extends User implements UserDetails {

    MyUserDetails(User user) {
        super.setId(user.getId());
        super.setLogin(user.getLogin());
        super.setHash(user.getHash());
        super.setRole(user.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(super.getRole().getName()));

        return roles;
    }

    @Override
    public String getUsername() {
        return super.getLogin();
    }

    @Override
    public String getPassword() {
        return super.getHash();
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
