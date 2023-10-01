package com.example.springjwt.service;

import com.example.springjwt.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    List<Product> getAll();
}
