package com.usc.productDemo.service;

import com.usc.productDemo.TestAuthentication;
import com.usc.productDemo.beans.User;
import com.usc.productDemo.dao.UserDao;
import com.usc.productDemo.http.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void register() {
        UserService userService = new UserService();
        UserDao mockUserDao = Mockito.mock(UserDao.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService.userDao = mockUserDao;
        userService.passwordEncoder = passwordEncoder;

        User user = new User(0, "username", "password", new ArrayList<>());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encryptedPassword");
        when(mockUserDao.save(user)).thenReturn(user);
        Response res = userService.register(user);
        
        assertTrue(res.isSuccess());
    }

    @Test
    void changePassword() {
        UserService userService = new UserService();
        UserDao mockUserDao = Mockito.mock(UserDao.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService.userDao = mockUserDao;
        userService.passwordEncoder = passwordEncoder;
        TestAuthentication auth = new TestAuthentication();
        
        User user = new User(1, "username", "password", new ArrayList<>());
        User user1 = new User(1, "username","newPassword", new ArrayList<>());
        auth.setUser(user);
        
        
        when(mockUserDao.findByUsername("username")).thenReturn(user);
        when(passwordEncoder.encode("newPassword")).thenReturn("encryptedPassword");
        when(mockUserDao.save(user)).thenReturn(user);
        
        Response res = userService.changePassword(user1, auth);
        
        assertTrue(res.isSuccess());
    }
    
    @Test
    void deleteUser() {
        UserService userService = new UserService();
        UserDao mockUserDao = Mockito.mock(UserDao.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService.userDao = mockUserDao;
        userService.passwordEncoder = passwordEncoder;
        TestAuthentication auth = new TestAuthentication();

        User user = new User(1, "username", "password", new ArrayList<>());
        when(mockUserDao.findById(1)).thenReturn(Optional.of(user));
        Response res = userService.deleteUser(1);
        then(mockUserDao).should(times(1)).deleteById(1);
        
        assertTrue(res.isSuccess());
    }
}