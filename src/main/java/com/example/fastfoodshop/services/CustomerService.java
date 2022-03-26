package com.example.fastfoodshop.services;

import com.example.fastfoodshop.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findById(long id);

    List<Customer> findAll();
}
