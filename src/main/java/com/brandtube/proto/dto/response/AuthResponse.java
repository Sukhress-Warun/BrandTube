package com.brandtube.proto.dto.response;

import com.brandtube.proto.dto.response.config.APIResponseConfig;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@APIResponseConfig(classNameToSnakeCase = true)
public class AuthResponse {
    private String token;
    private Long userId;
    private String userType;
}
