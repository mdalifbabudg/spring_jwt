package com.example.springjwt.controller;

import com.example.springjwt.constant.MessageResponse;
import com.example.springjwt.entity.User;
import com.example.springjwt.payload.AuthRequest;
import com.example.springjwt.payload.AuthResponse;
import com.example.springjwt.payload.RegisterRequest;
import com.example.springjwt.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    ResponseEntity<MessageResponse> register(@RequestBody @Valid RegisterRequest registerRequest){
        return ResponseEntity.ok(userService.register(registerRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthRequest authRequest){
        return ResponseEntity.ok(userService.authenticate(authRequest));
    }
}
