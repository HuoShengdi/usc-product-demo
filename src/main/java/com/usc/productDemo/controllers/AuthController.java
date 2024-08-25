package com.usc.productDemo.controllers;

import com.usc.productDemo.http.Response;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/checklogin")
    public Response checkLogin(Authentication authentication) {
        return new Response(authentication != null);
    }
}