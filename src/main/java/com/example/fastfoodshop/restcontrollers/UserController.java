package com.example.fastfoodshop.restcontrollers;

import com.example.fastfoodshop.domain.User;
import com.example.fastfoodshop.exceptions.UserNotFoundException;
import com.example.fastfoodshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("{id}")
    User get(@PathVariable long id) {
        return userService.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void userNotFound() {
    }

    @GetMapping
    List<User> getAll() {
        return userService.findAll();
    }
}
