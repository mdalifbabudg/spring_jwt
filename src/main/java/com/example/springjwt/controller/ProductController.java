package com.example.springjwt.controller;

import com.example.springjwt.entity.Product;
import com.example.springjwt.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @GetMapping
    public List<Product> list() {
        return productService.getAll();
    }
}
