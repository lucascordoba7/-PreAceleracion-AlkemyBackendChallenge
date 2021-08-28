package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.UserDTO;
import com.lucascordoba.backendchallenge.models.User;
import com.lucascordoba.backendchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> listUsers() {
       List<User> entities= userRepository.findAll();
       List<UserDTO> dtos=new ArrayList<>();
       for(User entity:entities){
           dtos.add(UserDTO.from(entity));
       }
       return dtos;
    }

    @Override
    public User findUser(UserDTO userDTO) {
        return userRepository.findById(userDTO.getId()).orElse(null);
    }

    @Override
    public User findUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void insertUser(UserDTO user) {
        if(user.getRole()==null || !user.getRole().contains("ROLE_ADMIN")){
            user.setRole("ROLE_USER");}

            userRepository.save(user.buildEntity());



    }

    @Override
    public Boolean deleteUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
