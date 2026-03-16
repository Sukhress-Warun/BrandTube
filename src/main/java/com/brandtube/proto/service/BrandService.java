package com.brandtube.proto.service;

import com.brandtube.proto.entity.Brand;
import com.brandtube.proto.repository.BrandRepository;
import com.brandtube.proto.service.generic.Utils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandService {
    final BrandRepository brandRepository;
    final UserService userService;

    @Transactional
    public Brand registerBrand(Brand brand) {
        return Utils.saveEntityWithUserCheck(brand, brandRepository, userService);
    }

}
