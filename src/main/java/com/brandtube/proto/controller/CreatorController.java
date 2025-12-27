package com.brandtube.proto.controller;

import com.brandtube.proto.dto.request.LoginRequest;
import com.brandtube.proto.dto.response.AuthResponse;
import com.brandtube.proto.dto.response.config.APIResponse;
import com.brandtube.proto.dto.response.config.APIResponseUtil;
import com.brandtube.proto.service.CreatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/creator")
public class CreatorController {

    final CreatorService creatorService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<AuthResponse>> loginCreator(@RequestBody LoginRequest loginRequest) {
        return APIResponseUtil.ok(creatorService.AuthenticateCreator(loginRequest));
    }


}
