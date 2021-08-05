package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.GenreDTO;
import com.lucascordoba.backendchallenge.dto.UserDTO;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<UserDTO> listUsers();
    public User findUser(UserDTO userDTO);
    public User findUser(Long id);
    public Boolean insertUser(UserDTO userDTO);
    public Boolean deleteUser(UserDTO userDTO);
    Optional<User> findByUsername(String username);
}
