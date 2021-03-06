package com.example.fastfoodshop.services;

import com.example.fastfoodshop.domain.Role;
import com.example.fastfoodshop.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(long id);

    List<User> findAll();
    User save(User user);
    Role save(Role role);
    void addRoleTo(String username, String role);
    User get(String username);
}
