package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.UserDTO;
import com.lucascordoba.backendchallenge.security.JwtUtil;
import com.lucascordoba.backendchallenge.services.EmailService;
import com.lucascordoba.backendchallenge.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;
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
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        if(userService.insertUser(userDTO))
        {
            log.info(userDTO.getEmail());
            emailService.sendEmail(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
        }
        return ResponseEntity.status(400).body(userDTO);
    }

}
