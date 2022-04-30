package com.example.fastfoodshop.restcontrollers;

import com.example.fastfoodshop.domain.Order;
import com.example.fastfoodshop.domain.Product;
import com.example.fastfoodshop.exceptions.OrderNotFoundException;
import com.example.fastfoodshop.exceptions.ProductNotFoundException;
import com.example.fastfoodshop.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    List<Order> getAll() {
        return orderService.findAll();
    }
    @GetMapping("{id}")
    Order get(@PathVariable long id) {
        return orderService.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void orderNotFound() {
    }

    @PostMapping
    void post(@RequestBody @Valid Order order){
        orderService.create(order);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        orderService.delete(id);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String,String> wrongData(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        FieldError::getDefaultMessage));
    }
}
