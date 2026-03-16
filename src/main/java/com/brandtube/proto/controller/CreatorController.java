package com.brandtube.proto.controller;

import com.brandtube.proto.dto.entityDTO.CreatorDto;
import com.brandtube.proto.dto.marker.Create;
import com.brandtube.proto.dto.request.LoginRequest;
import com.brandtube.proto.dto.response.AuthResponse;
import com.brandtube.proto.entity.Creator;
import com.brandtube.proto.entity.roles.Role;
import com.brandtube.proto.mapper.Transform;
import com.brandtube.proto.response.constructor.APIResponse;
import com.brandtube.proto.response.constructor.APIResponseUtil;
import com.brandtube.proto.service.CreatorService;
import com.brandtube.proto.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@EnableMethodSecurity
@RequestMapping("/creator")
public class CreatorController {

    final UserService userService;
    final CreatorService creatorService;
    final Transform transform;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<AuthResponse>> loginCreator(@Validated @RequestBody LoginRequest loginRequest) {
        loginRequest.setRole(Role.CREATOR);
        return APIResponseUtil.ok(userService.AuthenticateUser(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<CreatorDto>> registerCreator(@Validated(Create.class) @RequestBody CreatorDto creatorDTO) {
        Creator creator = transform.toEntity(creatorDTO);
        Creator registeredCreator = creatorService.registerCreator(creator);
        return APIResponseUtil.created(transform.toDTO(registeredCreator));
    }

    @GetMapping("/secured")
    public ResponseEntity<APIResponse<Object>> securedCreatorEndpoint() {
        String message = creatorService.securedCreatorMethod();
        return APIResponseUtil.ok(null, message);
    }

}
