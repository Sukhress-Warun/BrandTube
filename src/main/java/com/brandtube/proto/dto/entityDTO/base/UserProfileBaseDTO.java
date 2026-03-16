package com.brandtube.proto.dto.entityDTO.base;

import com.brandtube.proto.dto.marker.Create;
import com.brandtube.proto.dto.marker.Delete;
import com.brandtube.proto.dto.marker.Update;
import com.brandtube.proto.response.constructor.APIResponseConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@APIResponseConfig(classNameToSnakeCase = true)
public abstract class UserProfileBaseDTO {
    @NotBlank(groups = {Update.class, Delete.class})
    @Null(groups = Create.class)
    private Long id;

    @Email
    @NotBlank(groups = {Create.class, Update.class})
    @Null(groups = Delete.class)
    private String email;

    @Size(min = 8)
    @NotBlank(groups = {Create.class})
    @Null(groups = Delete.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Size(min = 8)
    @Null(groups = {Delete.class, Create.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String newPassword;

    @NotBlank(groups = {Create.class, Update.class})
    @Null(groups = Delete.class)
    private String name;

    @NotBlank(groups = Update.class)
    @Null(groups = Delete.class)
    private String about;
}
