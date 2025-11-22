package com.proyec.crud.controller;

import com.proyec.crud.model.UserRq;
import com.proyec.crud.model.UserRs;
import com.proyec.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

@PostMapping
    public ResponseEntity<UserRs> create(@RequestBody UserRq request) {
        return ResponseEntity.ok(service.createUser(request));
    }

@GetMapping("/{id}")
    public ResponseEntity<UserRs> GetUser(@PathVariable UUID id) {
        return ResponseEntity.ok(service.GetUser(id));
    }

@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
    service.deleteUser(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

@GetMapping()
    public ResponseEntity<List<UserRs>>GetAll() {
        return ResponseEntity.ok(service.GetAll());
    }


@PutMapping ("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody UserRq request ) {
        service.updateUser(id, request);
        return ResponseEntity.ok(service.updateUser(id, request));
    }
}