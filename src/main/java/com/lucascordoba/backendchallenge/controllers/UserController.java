package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.models.User;
import com.lucascordoba.backendchallenge.repositories.UserRepository;
import com.lucascordoba.backendchallenge.security.JwtUtil;
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
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> createAuthenticationToken (@RequestParam(name = "username") String username,@RequestParam(name = "password")String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (username,password));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }
         UserDetails userDetails= userDetailsService.loadUserByUsername(username);
         String jwt=jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }
    @PostMapping("/register")
    public User createUser(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password")String password,
                           @RequestParam(name = "roles") String roles){
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(roles);
        return userRepository.save(user);



    }

    @GetMapping
    public String hello(){
        return "hello";
    }


}
