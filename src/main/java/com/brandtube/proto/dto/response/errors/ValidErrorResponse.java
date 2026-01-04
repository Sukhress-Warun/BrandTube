package com.brandtube.proto.dto.response.errors;

import com.brandtube.proto.dto.response.config.APIResponseCollectionConfig;
import com.brandtube.proto.dto.response.config.APIResponseConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@APIResponseConfig(isCollection = true, name = "errors")
@APIResponseCollectionConfig(fieldName = "errors")
public class ValidErrorResponse {
    public List<String> errors;
}
