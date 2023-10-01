package com.example.springjwt.service.Impl;

import com.example.springjwt.config.JwtUtil;
import com.example.springjwt.constant.ConstantValue;
import com.example.springjwt.constant.MessageResponse;
import com.example.springjwt.entity.User;
import com.example.springjwt.payload.AuthRequest;
import com.example.springjwt.payload.AuthResponse;
import com.example.springjwt.payload.RegisterRequest;
import com.example.springjwt.repository.UserRepository;
import com.example.springjwt.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MessageResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            return new MessageResponse(ConstantValue.emailAlreadyUsed);
        }

        User user = populateToEntity(registerRequest, new User());
        try {
            userRepository.save(user);
        } catch (Exception e){
            throw new RuntimeException("User not saved!" + e);
        }

        return new MessageResponse(user.getId(), ConstantValue.savedSuccessfully);
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = findByEmail(authentication.getName());
            final String accessToken = jwtUtil.generateToken(user);

            return new AuthResponse(authentication.getName(), accessToken);
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("UNAUTHORIZED");
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    private User populateToEntity(RegisterRequest registerRequest, User user) {
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return user;
    }
}
