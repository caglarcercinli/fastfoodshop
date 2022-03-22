package com.example.fastfoodshop.services;

import com.example.fastfoodshop.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(long id);

    List<Product> findAll();
}
