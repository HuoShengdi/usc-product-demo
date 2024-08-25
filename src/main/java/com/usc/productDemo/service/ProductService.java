package com.usc.productDemo.service;

import com.usc.productDemo.beans.Product;
import com.usc.productDemo.dao.ProductDao;
import com.usc.productDemo.http.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductDao productDao;
    
    public Response addProduct(Product product) {
        productDao.save(product);
        return new Response(true);
    }
    
    public Response updateProduct(Product product) {
        Optional<Product> p = productDao.findById(product.getId());
        if (p.isPresent()) {
            Product newProduct = p.get();
            newProduct.setUnitPrice(product.getUnitPrice());
            newProduct.setName(product.getName());
            productDao.save(newProduct);
            return new Response(true);
        } else {
            return new Response(false, "Product not found");
        }
    }
    
    public Response deleteProduct(int id) {
        if (productDao.findById(id).isPresent()) {
            productDao.deleteById(id);
            return new Response(true);
        } else {
            return new Response(false, "Product not found");
        }
    }
}