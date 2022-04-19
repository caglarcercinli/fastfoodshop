package com.example.fastfoodshop.restcontrollers;

import com.example.fastfoodshop.domain.Customer;
import com.example.fastfoodshop.exceptions.CustomerNotFoundException;
import com.example.fastfoodshop.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/customers")
class CustomerController {
    private final CustomerService customerService;
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{id}")
    Customer get(@PathVariable long id) {
        return customerService.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void customerNotFound() {
    }

    @GetMapping
    List<Customer> getAll() {
        return customerService.findAll();
    }
}
