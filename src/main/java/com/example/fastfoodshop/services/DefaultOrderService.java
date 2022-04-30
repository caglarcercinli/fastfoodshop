package com.example.fastfoodshop.services;

import com.example.fastfoodshop.domain.Order;
import com.example.fastfoodshop.exceptions.OrderNotFoundException;
import com.example.fastfoodshop.exceptions.ProductNotFoundException;
import com.example.fastfoodshop.repositories.OrderRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;

    public DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void create(Order order) {
        orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException();
        }
    }
}
