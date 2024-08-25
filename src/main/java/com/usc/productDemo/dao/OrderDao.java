package com.usc.productDemo.dao;

import com.usc.productDemo.beans.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(int userId);
    
    List<Order> findByIdAndUserId(int id, int userId);
}