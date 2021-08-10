package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.UserDTO;

public interface EmailService {
    void sendEmail(UserDTO user);
}
