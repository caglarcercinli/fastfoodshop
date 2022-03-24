package com.example.fastfoodshop;

import com.example.fastfoodshop.domain.Product;

import java.math.BigDecimal;

public class LombokTest {
    public static void main(String[] args) {
        Product product= new Product();
        product.setId(2);
        product.setName("test");
        product.setPrice(BigDecimal.ONE);
        System.out.println(product.toString());
    }
}
