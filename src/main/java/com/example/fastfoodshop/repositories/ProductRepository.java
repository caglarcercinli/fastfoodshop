package com.example.fastfoodshop.repositories;

import com.example.fastfoodshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
