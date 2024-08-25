package com.usc.productDemo.controllers;

import com.usc.productDemo.beans.User;
import com.usc.productDemo.dao.UserDao;
import com.usc.productDemo.http.Response;
import com.usc.productDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserDao userDao;
    
    @Autowired
    UserService userService;
    
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userDao.findAll();
    }
    
    @PostMapping
    public Response addUser(@RequestBody User user) {
        return userService.register(user);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public Response changeUser(@RequestBody User user, Authentication authentication) {
        return userService.changePassword(user, authentication);
    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}