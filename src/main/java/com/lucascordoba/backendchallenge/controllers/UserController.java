package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/auth/login")
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
    @GetMapping
    public String hello(){
        return "hello";
    }


}
