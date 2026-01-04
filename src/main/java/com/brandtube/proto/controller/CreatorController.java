package com.brandtube.proto.controller;

import com.brandtube.proto.dto.request.LoginRequest;
import com.brandtube.proto.dto.response.AuthResponse;
import com.brandtube.proto.dto.response.config.APIResponse;
import com.brandtube.proto.dto.response.config.APIResponseUtil;
import com.brandtube.proto.service.CreatorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@EnableMethodSecurity
@RequestMapping("/creator")
public class CreatorController {

    final CreatorService creatorService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<AuthResponse>> loginCreator(@RequestBody LoginRequest loginRequest) {
        return APIResponseUtil.ok(creatorService.AuthenticateCreator(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<Object>> registerCreator(@Valid @RequestBody com.brandtube.proto.entity.Creator creator) {
        return APIResponseUtil.created(null, creatorService.registerCreator(creator));
    }

    @GetMapping("/secured")
    public ResponseEntity<APIResponse<Object>> securedCreatorEndpoint() {
        String message = creatorService.securedCreatorMethod();
        return APIResponseUtil.ok(null, message);
    }

}
