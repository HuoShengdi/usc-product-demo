package com.usc.productDemo.beans;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="sp_user")
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "SP_USER_SEQ")
    private int id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "c_user_c_user_profile", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    })
    private List<UserProfile> profiles = new ArrayList<>();

    public User() {
    }

    public User(int id, String username, String password, List<UserProfile> profiles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profiles = profiles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<UserProfile> profiles) {
        this.profiles = profiles;
    }

    public static long getSerialVersionUID() {
        return User.serialVersionUID;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles;
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