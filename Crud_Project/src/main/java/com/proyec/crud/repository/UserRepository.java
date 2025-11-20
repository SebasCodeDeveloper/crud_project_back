package com.proyec.crud.repository;

import com.proyec.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    // Método derivado (recomendado)
    Optional<User> findByEmail(String email);
}
