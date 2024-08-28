package com.usc.productDemo;

import com.usc.productDemo.beans.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestAuthentication implements Authentication {

    private List<GrantedAuthority> authorities = new ArrayList<>();
    private User user;

    public TestAuthentication() {
    }

    public TestAuthentication(List<GrantedAuthority> authorities, User user) {
        this.authorities = authorities;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}