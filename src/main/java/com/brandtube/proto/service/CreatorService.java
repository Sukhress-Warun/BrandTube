package com.brandtube.proto.service;

import com.brandtube.proto.entity.Creator;

import com.brandtube.proto.repository.CreatorRepository;
import com.brandtube.proto.service.generic.Utils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatorService {


    final CreatorRepository creatorRepository;
    final UserService userService;

    @Transactional
    public Creator registerCreator(Creator creator) {
        return Utils.saveEntityWithUserCheck(creator, creatorRepository, userService);
    }

    public String securedCreatorMethod() {
        return "Accessed secured endpoint for Creator successfully!";
    }
}
