package com.example.springjwt.service;

import com.example.springjwt.constant.MessageResponse;
import com.example.springjwt.entity.User;
import com.example.springjwt.payload.AuthRequest;
import com.example.springjwt.payload.AuthResponse;
import com.example.springjwt.payload.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService {
    MessageResponse register(RegisterRequest registerRequest);
    AuthResponse authenticate(AuthRequest authRequest);
    User findByEmail(String email);
}
