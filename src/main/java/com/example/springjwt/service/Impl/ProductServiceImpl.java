package com.example.springjwt.service.Impl;

import com.example.springjwt.entity.Product;
import com.example.springjwt.repository.ProductRepository;
import com.example.springjwt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product create(Product product) {
        try{
            productRepository.save(product);
        } catch (Exception e){
            throw new RuntimeException("Product not saved!" + e);
        }
        return product;
    }
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
