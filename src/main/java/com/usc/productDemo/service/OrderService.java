package com.usc.productDemo.service;

import com.usc.productDemo.beans.Order;
import com.usc.productDemo.beans.OrderProduct;
import com.usc.productDemo.beans.Product;
import com.usc.productDemo.beans.User;
import com.usc.productDemo.dao.OrderDao;
import com.usc.productDemo.dao.ProductDao;
import com.usc.productDemo.http.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderDao orderDao;
    
    @Autowired
    ProductDao productDao;
    
    public Response placeOrder(Order order, Authentication auth) {
        try {
            List<OrderProduct> purchases = order.getPurchases();
            for(OrderProduct op : purchases) {
                Product product = productDao.findById(op.getProduct().getId()).get();
                op.setProduct(product);
                op.setOrder(order);
            }
            order.setUser((User)auth.getPrincipal());
            order.setPurchasingDate(Date.from(Instant.now()));
            orderDao.save(order);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false);
        }
    }
    
    public List<Order> getOrders(Authentication auth) {
        try {
            if (isAdmin(auth.getAuthorities())) {
                return orderDao.findAll();
            }
            int authId = ((User)auth.getPrincipal()).getId();
            return orderDao.findByUserId(authId);
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }
    
    public Order getOrderForUser(int orderId, Authentication auth) {
        try {
            if (isAdmin(auth.getAuthorities())) {
                return orderDao.findById(orderId).get();
            }
            int authId = ((User)auth.getPrincipal()).getId();
            List<Order> orderList = orderDao.findByIdAndUserId(orderId, authId);

            return orderList.get(0);
        } catch (Exception e) {
            return null;
        }
    }
    
    public Response editOrder(Order order, Authentication auth) {
        try {
            Optional<Order> o = orderDao.findById(order.getId());
            if (o.isPresent()) {
                Order newOrder = o.get();
                newOrder.setPurchases(order.getPurchases());
                newOrder.setPurchasingDate(order.getPurchasingDate());
                orderDao.save(newOrder);
                return new Response(true);
            } else {
                return new Response(false);
            }
        } catch (Exception e) {
            return new Response(false);
        }
    }

    public boolean isAdmin(Collection<? extends GrantedAuthority> profiles) {
        boolean isAdmin = false;
        for(GrantedAuthority profile : profiles) {
            if(profile.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        return isAdmin;
    }
}