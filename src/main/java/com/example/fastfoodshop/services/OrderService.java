package com.example.fastfoodshop.services;

import com.example.fastfoodshop.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void create(Order order);
    List<Order> findAll();
    Optional<Order> findById(long id);
    void delete(long id);
}
