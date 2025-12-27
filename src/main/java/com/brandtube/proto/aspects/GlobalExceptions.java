package com.brandtube.proto.aspects;

import com.brandtube.proto.dto.response.config.APIResponse;
import com.brandtube.proto.dto.response.config.APIResponseUtil;
import com.brandtube.proto.exceptions.CustomExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<APIResponse<Void>> handleCustomException(CustomExceptions ex) {
        return APIResponseUtil.unauthorized(null, ex.getMessage());
    }

}
