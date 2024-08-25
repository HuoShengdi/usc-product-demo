package com.usc.productDemo.controllers;

import com.usc.productDemo.TestAuthentication;
import com.usc.productDemo.http.Response;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    @Test
    void checkLogin() {
        AuthController authController = new AuthController();
        Authentication auth = new TestAuthentication();
        Response response = authController.checkLogin(auth);
        assertTrue(response.isSuccess());
    }
}