package com.brandtube.proto.service;

import com.brandtube.proto.dto.request.LoginRequest;
import com.brandtube.proto.dto.response.AuthResponse;
import com.brandtube.proto.entity.Brand;
import com.brandtube.proto.security.role.Role;
import com.brandtube.proto.exceptions.CustomExceptions;
import com.brandtube.proto.repository.BrandRepository;
import com.brandtube.proto.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class BrandService {

    final JwtUtil jwtUtil;
    final PasswordEncoder passwordEncoder;
    final BrandRepository brandRepository;


    public AuthResponse AuthenticateBrand(LoginRequest loginRequest){
        Optional<Brand> brandOptional = brandRepository.findByEmail(loginRequest.getEmail());
        if (brandOptional.isPresent()) {
            Brand brand = brandOptional.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), brand.getPassword())) {
                String token = jwtUtil.generateToken(brand.getId(), Role.BRAND.name(), brand.getEmail());
                return AuthResponse.builder().token(token).userId(brand.getId()).userType(Role.BRAND.name()).build();
            }
        }
        throw new CustomExceptions("The provided " + Role.BRAND.name().toLowerCase() + " login credentials are invalid.");
    }

}
