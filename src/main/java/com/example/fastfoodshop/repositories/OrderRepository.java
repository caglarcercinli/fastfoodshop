package com.example.fastfoodshop.repositories;

import com.example.fastfoodshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
}
