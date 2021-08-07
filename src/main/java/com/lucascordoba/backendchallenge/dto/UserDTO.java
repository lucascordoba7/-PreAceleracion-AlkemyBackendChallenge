package com.lucascordoba.backendchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucascordoba.backendchallenge.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String role;

    public User buildEntity(){
        return User.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .role(this.role)
                .build();
    }
    public static UserDTO from(User entity){
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();
    }
}
