package com.example.fastfoodshop.restcontrollers;

import com.example.fastfoodshop.domain.Product;
import com.example.fastfoodshop.exceptions.ProductNotFoundException;
import com.example.fastfoodshop.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
class ProductController {
    private final ProductService productService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

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
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    List<Product> getAll() {
        logger.info("All products are provided");
        return productService.findAll();
    }
    @PostMapping
    void post(@RequestBody @Valid Product product){
        productService.create(product);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String,String> wrongData(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        FieldError::getDefaultMessage));
    }
    
    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        productService.delete(id);
    }
}
