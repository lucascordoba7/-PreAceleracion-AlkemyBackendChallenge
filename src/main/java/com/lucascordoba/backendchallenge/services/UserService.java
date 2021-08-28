package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.GenreDTO;
import com.lucascordoba.backendchallenge.dto.UserDTO;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> listUsers();
    User findUser(UserDTO userDTO);
    User findUser(Long id);
    void insertUser(UserDTO userDTO);
    Boolean deleteUser(UserDTO userDTO);
    Optional<User> findByUsername(String username);
}
