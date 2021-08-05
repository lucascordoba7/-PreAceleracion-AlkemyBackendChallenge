package com.lucascordoba.backendchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_username",unique = true)
    private String username;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_role")
    private String role;

}
