package com.usc.productDemo.controllers;

import com.usc.productDemo.beans.User;
import com.usc.productDemo.dao.UserDao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Test
    void getUsers() {
        UserController userController = new UserController();
        UserDao userDao = mock(UserDao.class);
        userController.userDao = userDao;
        
        User user = new User(1, "username", "password", new ArrayList<>());
        when(userDao.findAll()).thenReturn(List.of(user));
        
        assertEquals(1, userController.getUsers().size());
    }

    @Test
    void addUser() {
    }

    @Test
    void changeUser() {
    }

    @Test
    void deleteUser() {
    }
}