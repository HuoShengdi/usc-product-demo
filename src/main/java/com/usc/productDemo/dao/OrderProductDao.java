package com.usc.productDemo.dao;

import com.usc.productDemo.beans.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductDao extends JpaRepository<OrderProduct, Integer> {
    List<OrderProduct> findByOrderId(int orderId);
}