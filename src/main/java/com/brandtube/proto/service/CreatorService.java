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
                AuthResponse data = AuthResponse.builder().token(token).userId(creator.getId()).userType(Role.CREATOR.name()).build();
                System.out.println(data);
                return data;
            }
        }
        throw new CustomExceptions("The provided " + Role.CREATOR.name().toLowerCase() + " login credentials are invalid.");
    }

    public String registerCreator(Creator creator) {
        if (creatorRepository.findByEmail(creator.getEmail()).isPresent()) {
            throw new CustomExceptions("Creator with email " + creator.getEmail() + " already exists");
        }
        creator.setPassword(passwordEncoder.encode(creator.getPassword()));
        Creator savedCreator = creatorRepository.save(creator);
        return "Creator registered with ID: " + savedCreator.getId();
    }

    public String securedCreatorMethod() {
        return "Accessed secured endpoint for Creator successfully!";
    }
}
