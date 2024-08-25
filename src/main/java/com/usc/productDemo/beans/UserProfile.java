package com.usc.productDemo.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "sp_user_profile")
public class UserProfile implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    @Column
    private String type;

    public UserProfile() {
    }

    public UserProfile(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return UserProfile.serialVersionUID;
    }

    @Override
    public String getAuthority() {
        return type;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}