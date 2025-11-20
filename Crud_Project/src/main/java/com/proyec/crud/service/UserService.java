package com.proyec.crud.service;

import com.proyec.crud.entity.User;
import com.proyec.crud.model.UserRq;
import com.proyec.crud.model.UserRs;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserRs createUser(UserRq request);

    UserRs GetUser (UUID id);

    List<UserRs> GetAll();

    UserRs updateUser(UUID id, UserRq request);

    void deleteUser (UUID id);
}
