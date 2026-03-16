package com.brandtube.proto.mapper;

import com.brandtube.proto.dto.entityDTO.BrandDto;
import com.brandtube.proto.dto.entityDTO.CreatorDto;
import com.brandtube.proto.entity.Brand;
import com.brandtube.proto.entity.Creator;
import org.mapstruct.Ignored;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Transform {

    @Mapping(source = "email", target = "user.email")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "name", target = "user.name")
    @Mapping(source = "about", target = "user.about")
    @Mapping(target = "user.role", expression  = "java(com.brandtube.proto.entity.roles.Role.CREATOR)")
    Creator toEntity(CreatorDto creatorDTO);

    @Mapping(source = "email", target = "user.email")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "name", target = "user.name")
    @Mapping(source = "about", target = "user.about")
    @Mapping(target = "user.role", expression  = "java(com.brandtube.proto.entity.roles.Role.BRAND)")
    Brand toEntity(BrandDto brand);

    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.about", target = "about")
    @Ignored(targets = "password")
    CreatorDto toDTO(Creator creator);

    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.about", target = "about")
    @Ignored(targets = "password")
    BrandDto toDTO(Brand brand);
}