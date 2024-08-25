package com.usc.productDemo.controllers;

import com.usc.productDemo.beans.Product;
import com.usc.productDemo.dao.ProductDao;
import com.usc.productDemo.http.Response;
import com.usc.productDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductDao productDao;
    
    @Autowired
    ProductService productService;
    
    @GetMapping
    public List<Product> getProducts() {
        return productDao.findAll();
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Response addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
    
    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Response updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Response deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}