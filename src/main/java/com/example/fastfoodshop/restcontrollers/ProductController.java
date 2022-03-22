package com.example.fastfoodshop.restcontrollers;

import com.example.fastfoodshop.domain.Product;
import com.example.fastfoodshop.exceptions.ProductNotFoundException;
import com.example.fastfoodshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    Product get(@PathVariable long id) {
        return productService.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void productNotFound() {
    }

    @GetMapping
    List<Product> getAll(){
        return productService.findAll();
    }
}
