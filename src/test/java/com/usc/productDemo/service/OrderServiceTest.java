package com.usc.productDemo.service;

import com.usc.productDemo.TestAuthentication;
import com.usc.productDemo.beans.*;
import com.usc.productDemo.dao.OrderDao;
import com.usc.productDemo.dao.OrderProductDao;
import com.usc.productDemo.dao.ProductDao;
import com.usc.productDemo.http.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceTest {

    @Test
    void placeOrder() {
        OrderService orderService = new OrderService();
        OrderDao mockOrderDao = Mockito.mock(OrderDao.class);
        ProductDao mockProductDao = Mockito.mock(ProductDao.class);
        OrderProductDao mockOrderProductDao = Mockito.mock(OrderProductDao.class);
        orderService.orderDao = mockOrderDao;
        orderService.productDao = mockProductDao;
        orderService.orderProductDao = mockOrderProductDao;
        TestAuthentication auth = new TestAuthentication();
        
        User user = new User(1, "foo", "bar", new ArrayList<>());
        when(auth.getPrincipal()).thenReturn(user);
        Product product = new Product(1, "baz", new BigDecimal("0.99"));
        OrderProduct orderProduct = new OrderProduct(1, null, product,1, new BigDecimal("0.99"));
        List<OrderProduct> purchaseList = new ArrayList<>();
        purchaseList.add(orderProduct);
        
        Order order = new Order(1, user, purchaseList, Date.from(Instant.now()));
        when(mockOrderDao.save(order)).thenReturn(order);
        when(mockOrderProductDao.save(orderProduct)).thenReturn(orderProduct);
        when(mockProductDao.findById(1)).thenReturn(Optional.of(product));
        Response res = orderService.placeOrder(order, auth);
        
        assertTrue(res.isSuccess());
    }

    @Test
    void getOrders() {
        OrderService orderService = new OrderService();
        OrderDao mockOrderDao = Mockito.mock(OrderDao.class);
        ProductDao mockProductDao = Mockito.mock(ProductDao.class);
        OrderProductDao mockOrderProductDao = Mockito.mock(OrderProductDao.class);
        orderService.orderDao = mockOrderDao;
        orderService.productDao = mockProductDao;
        orderService.orderProductDao = mockOrderProductDao;
        TestAuthentication auth = new TestAuthentication();
        
        User user = new User(1, "foo", "bar", new ArrayList<>());

        UserProfile userProfile = new UserProfile(1);
        userProfile.setType("ROLE_ADMIN");
        List<UserProfile> profiles = new ArrayList<>();
        profiles.add(userProfile);
        User admin = new User(2, "admin", "ruler", profiles);
        
        Order userOrder = new Order(1, user, new ArrayList<>(), Date.from(Instant.now()));
        Order adminOrder = new Order(2, admin, new ArrayList<>(), Date.from(Instant.now()));
        when(mockOrderDao.findAll()).thenReturn(List.of(userOrder, adminOrder));
        when(mockOrderDao.findByUserId(1)).thenReturn(List.of(userOrder));
        
        auth.setUser(user);
        List<Order> userOrders = orderService.getOrders(auth);
        assertEquals(1, userOrders.size());
        
        GrantedAuthority adminRole = new SimpleGrantedAuthority("ROLE_ADMIN");
        auth.setAuthorities(List.of(adminRole));
        auth.setUser(admin);
        
        List<Order> adminOrders = orderService.getOrders(auth);
        assertEquals(2, adminOrders.size());

    }

    @Test
    void getOrderForUser() {
        OrderService orderService = new OrderService();
        OrderDao mockOrderDao = Mockito.mock(OrderDao.class);
        ProductDao mockProductDao = Mockito.mock(ProductDao.class);
        OrderProductDao mockOrderProductDao = Mockito.mock(OrderProductDao.class);
        orderService.orderDao = mockOrderDao;
        orderService.productDao = mockProductDao;
        orderService.orderProductDao = mockOrderProductDao;
        TestAuthentication auth = new TestAuthentication();

        User user = new User(1, "foo", "bar", new ArrayList<>());
        Order userOrder = new Order(1, user, new ArrayList<>(), Date.from(Instant.now()));
        when(mockOrderDao.findByIdAndUserId(1, 1)).thenReturn(List.of(userOrder));
        
        auth.setUser(user);
        Order result = orderService.getOrderForUser(1, auth);
        assertEquals(userOrder, result);
    }

    @Test
    void editOrder() {
        OrderService orderService = new OrderService();
        OrderDao mockOrderDao = Mockito.mock(OrderDao.class);
        ProductDao mockProductDao = Mockito.mock(ProductDao.class);
        OrderProductDao mockOrderProductDao = Mockito.mock(OrderProductDao.class);
        orderService.orderDao = mockOrderDao;
        orderService.productDao = mockProductDao;
        orderService.orderProductDao = mockOrderProductDao;
        TestAuthentication auth = new TestAuthentication();

        User user = new User(1, "foo", "bar", new ArrayList<>());
        Order userOrder = new Order(1, user, new ArrayList<>(), Date.from(Instant.now()));
        Product product = new Product(3, "item", new BigDecimal("0.99"));
        when(mockProductDao.findById(3)).thenReturn(Optional.of(product));
        when(mockOrderDao.findById(1)).thenReturn(Optional.of(userOrder));
        
        
        OrderProduct purchase = new OrderProduct(2,null, product, 1, new BigDecimal("0.99"));
        Order newOrder = new Order(1, user, List.of(purchase), null);
        when(mockOrderProductDao.save(purchase)).thenReturn(purchase);
        Response res = orderService.editOrder(newOrder, auth);
        
        assertTrue(res.isSuccess());
    }
}