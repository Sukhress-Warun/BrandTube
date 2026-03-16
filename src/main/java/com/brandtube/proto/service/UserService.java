package com.brandtube.proto.service;

import com.brandtube.proto.dto.request.LoginRequest;
import com.brandtube.proto.dto.response.AuthResponse;
import com.brandtube.proto.entity.User;
import com.brandtube.proto.exceptions.CustomExceptions;
import com.brandtube.proto.repository.UserRepository;
import com.brandtube.proto.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    final JwtUtil jwtUtil;
    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;


    @Transactional
    public AuthResponse AuthenticateUser(LoginRequest loginRequest){
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getRole().name().equals(loginRequest.getRole().name()) && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getId(), user.getRole().name(), user.getEmail());
                return AuthResponse.builder().token(token).userId(user.getId()).userType(user.getRole().name()).build();
            }
        }
        throw new CustomExceptions("The provided " + loginRequest.getRole().name().toLowerCase() + " login credentials are invalid.");
    }

    public boolean checkForExistingUser(User user) {
        return userRepository.findByEmailAndRole(user.getEmail(), user.getRole()).isPresent();
    }

    public void encodeUserPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}