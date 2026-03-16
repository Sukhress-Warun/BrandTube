package com.brandtube.proto.dto.response.errors;

import com.brandtube.proto.response.constructor.APIResponseCollectionConfig;
import com.brandtube.proto.response.constructor.APIResponseConfig;
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
public class InvalidErrorResponse {
    public List<String> errors;
}
