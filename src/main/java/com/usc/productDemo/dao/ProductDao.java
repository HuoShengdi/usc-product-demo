package com.usc.productDemo.dao;

import com.usc.productDemo.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
}