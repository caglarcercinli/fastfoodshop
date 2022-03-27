package com.example.fastfoodshop.repositories;

import com.example.fastfoodshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
