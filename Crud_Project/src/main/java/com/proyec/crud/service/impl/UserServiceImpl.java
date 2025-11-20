package com.proyec.crud.service.impl;

import com.proyec.crud.entity.User;
import com.proyec.crud.model.UserRq;
import com.proyec.crud.model.UserRs;
import com.proyec.crud.repository.UserRepository;
import com.proyec.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository UserRepository;

    @Override
    public UserRs createUser(UserRq request) {

        // Validar si el email ya existe
        UserRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("El email ya está registrado");
                });

User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        // Guardar usuario
        User userSaved = UserRepository.save(user);

        // Devolver respuesta sin contraseña
        return toRs(userSaved);
    }

    @Override
    public UserRs GetUser(UUID id) {
        User user = UserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        return toRs(user);
    }

    @Override
    public List<UserRs> GetAll() {
        return UserRepository.findAll().stream().map(this::toRs).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(UUID id) {
        User user = UserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));
        UserRepository.delete(user);
        new UserRs("Usuario eliminado", user.getName());
    }


    @Override
    public UserRs updateUser(UUID id, UserRq request) {
        // Buscar el usuario
        User user = UserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        // Validar que el nuevo email no esté usado por otro usuario
        UserRepository.findByEmail(request.getEmail())
                .filter(u -> !u.getId().equals(id))
                .ifPresent(u -> {
                    throw new RuntimeException("El email ya esta registrado por otro usuario");
                });

        //Actualizar Datos
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        User userUpdate = UserRepository.save(user);

        // Devolver respuesta sin contraseña
        return toRs(userUpdate);
    }

    private UserRs toRs(User user){
        UserRs rs =  new UserRs();

        rs.setName(user.getName());
        rs.setEmail(user.getEmail());

        return rs;

    }


}


