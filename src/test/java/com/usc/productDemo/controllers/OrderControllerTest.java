package com.usc.productDemo.controllers;

import com.usc.productDemo.TestAuthentication;
import com.usc.productDemo.beans.Order;
import com.usc.productDemo.http.Response;
import com.usc.productDemo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    @Test
    void getOrders() {
        OrderController orderController = new OrderController();
        OrderService mockOrderService = Mockito.mock(OrderService.class);
        orderController.orderService = mockOrderService;
        Authentication auth = new TestAuthentication();
        
        Order testOrder = new Order();
        List<Order> orderList = new ArrayList<>();
        orderList.add(testOrder);
        when(mockOrderService.getOrders(auth)).thenReturn(orderList);
        
        assertEquals(orderController.getOrders(auth), orderList);
    }

    @Test
    void getOrder() {
        OrderController orderController = new OrderController();
        OrderService mockOrderService = Mockito.mock(OrderService.class);
        orderController.orderService = mockOrderService;
        Authentication auth = new TestAuthentication();
        
        Order testOrder = new Order(1,null, null, null);
        when(mockOrderService.getOrderForUser(1, auth)).thenReturn(testOrder);
        
        assertEquals(orderController.getOrder(1, auth), testOrder);
    }

    @Test
    void placeOrder() {
        OrderController orderController = new OrderController();
        OrderService mockOrderService = Mockito.mock(OrderService.class);
        orderController.orderService = mockOrderService;
        Authentication auth = new TestAuthentication();
        
        Response res = new Response(true);
        Order testOrder = new Order();
        when(mockOrderService.placeOrder(testOrder, auth)).thenReturn(res);
        
        assertEquals(orderController.placeOrder(testOrder, auth), res);
    }

    @Test
    void editOrder() {
        OrderController orderController = new OrderController();
        OrderService mockOrderService = Mockito.mock(OrderService.class);
        orderController.orderService = mockOrderService;
        Authentication auth = new TestAuthentication();

        Response res = new Response(true);
        Order testOrder = new Order();
        when(mockOrderService.editOrder(testOrder, auth)).thenReturn(res);
        
        assertEquals(orderController.editOrder(testOrder, auth), res);
    }
    
}