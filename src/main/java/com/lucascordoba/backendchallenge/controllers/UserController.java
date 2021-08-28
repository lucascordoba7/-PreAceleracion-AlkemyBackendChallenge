package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.UserDTO;
import com.lucascordoba.backendchallenge.security.JwtUtil;
import com.lucascordoba.backendchallenge.services.EmailService;
import com.lucascordoba.backendchallenge.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/auth")
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

    @PostMapping("/auth/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO userDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (userDTO.getUsername(), userDTO.getPassword()));
        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(403).body(e.getMessage());
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        log.info("login successful");
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.insertUser(userDTO);
            emailService.sendEmail(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(409).body(e.getMessage());
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok("Welcome");
    }

}

class JwtResponse {
    public String jwt;

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }
}
