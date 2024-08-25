package com.usc.productDemo.dao;

import com.usc.productDemo.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}