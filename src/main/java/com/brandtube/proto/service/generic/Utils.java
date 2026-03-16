package com.brandtube.proto.service.generic;

import com.brandtube.proto.entity.User;
import com.brandtube.proto.entity.base.UserProfileBaseEntity;
import com.brandtube.proto.exceptions.CustomExceptions;
import com.brandtube.proto.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

public class Utils {

    public static <T extends UserProfileBaseEntity> void checkExistingUserAndEncodePassword(T entity, UserService userService) {
        User user = entity.getUser();
        if (userService.checkForExistingUser(user)) {
            throw new CustomExceptions(user.getRole().name() + " with email " + user.getEmail() + " already exists");
        }
        userService.encodeUserPassword(user);
    }

    public static <T extends UserProfileBaseEntity> T saveEntityWithUserCheck(T entity, JpaRepository<T, ?> repository, UserService userService) {
        checkExistingUserAndEncodePassword(entity, userService);
        return repository.save(entity);
    }

}
