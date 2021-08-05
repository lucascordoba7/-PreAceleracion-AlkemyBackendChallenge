package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.UserDTO;
import com.lucascordoba.backendchallenge.models.User;
import com.lucascordoba.backendchallenge.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements  UserService{
    @Autowired
    UserRepository userRepository;
    ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<UserDTO> listUsers() {
       List<User> entities= userRepository.findAll();
       List<UserDTO> dtos=new ArrayList<>();
       for(User entity:entities){
           dtos.add(modelMapper.map(entity,UserDTO.class));
       }
       return dtos;
    }

    @Override
    public User findUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public User findUser(Long id) {
        return null;
    }

    @Override
    public User insertUser(UserDTO userDTO) {
        User user=modelMapper.map(userDTO,User.class);
        return userRepository.save(user);

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
