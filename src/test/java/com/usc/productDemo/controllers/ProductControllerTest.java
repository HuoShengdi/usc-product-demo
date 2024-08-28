package com.usc.productDemo.controllers;

import com.usc.productDemo.beans.Product;
import com.usc.productDemo.dao.ProductDao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Test
    void getProducts() {
        ProductController productController = new ProductController();
        ProductDao productDao = mock(ProductDao.class);
        productController.productDao = productDao;
        
        Product product = new Product(1, "foo",new BigDecimal("1.50"));
        when(productDao.findAll()).thenReturn(List.of(product));
        
        assertEquals(1, productController.getProducts().size());
        
    }

    @Test
    void addProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}