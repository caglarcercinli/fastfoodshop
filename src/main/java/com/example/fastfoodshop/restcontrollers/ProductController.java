package com.example.fastfoodshop.restcontrollers;

import com.example.fastfoodshop.domain.Product;
import com.example.fastfoodshop.exceptions.ProductNotFoundException;
import com.example.fastfoodshop.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
class ProductController {
    private final ProductService productService;
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    Product get(@PathVariable long id) {
        logger.info("product id: "+id+" requested");
        return productService.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void productNotFound() {
        logger.warn("vacant product is requested");
    }

    @GetMapping
    List<Product> getAll() {
        logger.info("All products are provided");
        return productService.findAll();
    }
}
