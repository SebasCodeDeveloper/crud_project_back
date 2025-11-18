package com.proyec.crud.controller;

import com.proyec.crud.entity.User;
import com.proyec.crud.model.UserRs;
import com.proyec.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
    @RequestMapping("/api/users")

public class UserController {

    private final UserService service;

@PostMapping
    public ResponseEntity<UserRs> create(@RequestBody User user) {
        return ResponseEntity.ok(service.createUser(user));
    }

@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
    service.deleteUser(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");

    }
}