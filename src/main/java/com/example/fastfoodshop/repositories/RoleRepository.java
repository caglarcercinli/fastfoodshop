package com.example.fastfoodshop.repositories;

import com.example.fastfoodshop.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
