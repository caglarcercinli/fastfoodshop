package com.example.fastfoodshop.restcontrollers;

import com.example.fastfoodshop.domain.Customer;
import com.example.fastfoodshop.exceptions.CustomerNotFoundException;
import com.example.fastfoodshop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
class CustomerController {
    private final CustomerService customerService;

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
