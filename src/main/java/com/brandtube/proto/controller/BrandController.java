package com.brandtube.proto.controller;

import com.brandtube.proto.dto.request.LoginRequest;
import com.brandtube.proto.dto.response.config.APIResponseUtil;
import com.brandtube.proto.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/brand")
@AllArgsConstructor
public class BrandController {

    final BrandService brandService;

    @PostMapping("/login")
    public ResponseEntity<?> loginBrand(@RequestBody LoginRequest loginRequest) {
        return APIResponseUtil.ok(brandService.AuthenticateBrand(loginRequest));
    }



}

