package com.usc.productDemo.controllers;

import com.usc.productDemo.beans.Order;
import com.usc.productDemo.http.Response;
import com.usc.productDemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public List<Order> getOrders(Authentication auth) {
        return orderService.getOrders(auth);
    }
    
    @GetMapping("/{orderId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public Order getOrder(@PathVariable int orderId, Authentication auth) {
        return orderService.getOrderForUser(orderId, auth);
    }
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public Response placeOrder(@RequestBody Order order, Authentication auth) {
        return orderService.placeOrder(order, auth);
    }
    
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public Response editOrder(@RequestBody Order order, Authentication auth) {
        return orderService.editOrder(order, auth);
    }
    
}