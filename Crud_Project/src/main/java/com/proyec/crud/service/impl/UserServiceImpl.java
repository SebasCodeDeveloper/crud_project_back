package com.proyec.crud.service.impl;

import com.proyec.crud.entity.User;
import com.proyec.crud.model.UserRs;
import com.proyec.crud.repository.UserRepository;
import com.proyec.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public UserRs createUser(User user) {

        // Validar si el email ya existe
        repository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("El email ya está registrado");
                });

        // Guardar usuario
        User userSaved = repository.save(user);

        // Devolver respuesta sin contraseña
        return toRs(userSaved);
    }



    @Override
    public void deleteUser(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));
        repository.delete(user);
        new UserRs("Usuario eliminado", user.getName());
    }

    private UserRs toRs(User user){
        UserRs rs =  new UserRs();

        rs.setName(user.getName());
        rs.setEmail(user.getEmail());

        return rs;

    }
}


