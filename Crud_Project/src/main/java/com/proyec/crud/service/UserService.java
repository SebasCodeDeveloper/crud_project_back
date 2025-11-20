package com.proyec.crud.service;

import com.proyec.crud.entity.User;
import com.proyec.crud.model.UserRs;

import java.util.UUID;

public interface UserService {

    UserRs createUser(User user);

    void deleteUser (UUID id);
}
