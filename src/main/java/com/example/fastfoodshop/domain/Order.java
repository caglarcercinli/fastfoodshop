package com.example.fastfoodshop.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    public Order(long customerId) {
//        this.customerId = customerId;
//    }
//
//    protected Order() {
//    }
//
//
//    public long getId() {
//        return id;
//    }
//
//    public long getCustomerId() {
//        return customerId;
//    }
}
