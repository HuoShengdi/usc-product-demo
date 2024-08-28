package com.usc.productDemo.service;

import com.usc.productDemo.beans.Product;
import com.usc.productDemo.dao.ProductDao;
import com.usc.productDemo.http.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Test
    void addProduct() {
        ProductService productService = new ProductService();
        ProductDao mockProductDao = Mockito.mock(ProductDao.class);
        productService.productDao = mockProductDao;
        
        Product product = new Product(1, "foo",new BigDecimal("0.99"));
        when(mockProductDao.save(product)).thenReturn(product);
        
        Response res = productService.addProduct(product);
        assertTrue(res.isSuccess());
    }

    @Test
    void updateProduct() {
        ProductService productService = new ProductService();
        ProductDao mockProductDao = Mockito.mock(ProductDao.class);
        productService.productDao = mockProductDao;

        Product product = new Product(1, "foo",new BigDecimal("0.99"));
        when(mockProductDao.findById(1)).thenReturn(Optional.of(product));
        when(mockProductDao.save(product)).thenReturn(product);
        Product newProduct = new Product(1, "foo", new BigDecimal("1.99"));
        Response res = productService.updateProduct(newProduct);
        
        assertTrue(res.isSuccess());
    }

    @Test
    void deleteProduct() {
        ProductService productService = new ProductService();
        ProductDao mockProductDao = Mockito.mock(ProductDao.class);
        productService.productDao = mockProductDao;
        

        Product product = new Product(1, "foo",new BigDecimal("0.99"));
        when(mockProductDao.findById(1)).thenReturn(Optional.of(product));

        Response res = productService.deleteProduct(1);
        then(mockProductDao).should(times(1)).deleteById(1);

        assertTrue(res.isSuccess());
    }
}