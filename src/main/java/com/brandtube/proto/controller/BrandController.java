package com.brandtube.proto.controller;

import com.brandtube.proto.dto.entityDTO.BrandDto;
import com.brandtube.proto.dto.marker.Create;
import com.brandtube.proto.dto.request.LoginRequest;
import com.brandtube.proto.entity.Brand;
import com.brandtube.proto.entity.roles.Role;
import com.brandtube.proto.mapper.Transform;
import com.brandtube.proto.response.constructor.APIResponse;
import com.brandtube.proto.response.constructor.APIResponseUtil;
import com.brandtube.proto.service.BrandService;
import com.brandtube.proto.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/brand")
@AllArgsConstructor
public class BrandController {

    final UserService userService;
    final BrandService brandService;
    final Transform transform;


    @PostMapping("/login")
    public ResponseEntity<?> loginBrand(@Validated @RequestBody LoginRequest loginRequest) {
        loginRequest.setRole(Role.BRAND);
        return APIResponseUtil.ok(userService.AuthenticateUser(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<BrandDto>> registerBrand(@Validated(Create.class) @RequestBody BrandDto brandDTO) {
        Brand brand = transform.toEntity(brandDTO);
        Brand registeredBrand = brandService.registerBrand(brand);
        return APIResponseUtil.created(transform.toDTO(registeredBrand));
    }

}
