package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.UserDTO;
import com.lucascordoba.backendchallenge.models.User;
import com.lucascordoba.backendchallenge.repositories.UserRepository;
import com.lucascordoba.backendchallenge.security.JwtUtil;
import com.lucascordoba.backendchallenge.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> createAuthenticationToken (@RequestBody UserDTO userDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (userDTO.getUsername(),userDTO.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }
         UserDetails userDetails= userDetailsService.loadUserByUsername(userDTO.getUsername());
         String jwt=jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }
    @PostMapping("/register")
    public User createUser(@RequestBody UserDTO userDTO){
        return userService.insertUser(userDTO);
    }

    @GetMapping
    public String hello(){
        return "hello";
    }


}
