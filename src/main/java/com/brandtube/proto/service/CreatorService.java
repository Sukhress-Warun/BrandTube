package com.brandtube.proto.service;

import com.brandtube.proto.dto.request.LoginRequest;
import com.brandtube.proto.dto.response.AuthResponse;
import com.brandtube.proto.entity.Creator;
import com.brandtube.proto.security.role.Role;
import com.brandtube.proto.exceptions.CustomExceptions;
import com.brandtube.proto.repository.CreatorRepository;
import com.brandtube.proto.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CreatorService {


    final CreatorRepository creatorRepository;
    final JwtUtil jwtUtil;
    final PasswordEncoder passwordEncoder;

    public AuthResponse AuthenticateCreator(LoginRequest loginRequest){
        Optional<Creator> creatorOptional = creatorRepository.findByEmail(loginRequest.getEmail());
        if (creatorOptional.isPresent()) {
            Creator creator = creatorOptional.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), creator.getPassword())) {
                String token = jwtUtil.generateToken(creator.getId(), Role.CREATOR.name(), creator.getEmail());
                return AuthResponse.builder().token(token).userId(creator.getId()).userType(Role.CREATOR.name()).build();
            }
        }
        throw new CustomExceptions("Unauthorized " + Role.CREATOR.name().toLowerCase());
    }
}
