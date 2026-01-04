package com.brandtube.proto.aspects;

import com.brandtube.proto.dto.response.errors.ValidErrorResponse;
import com.brandtube.proto.dto.response.config.APIResponse;
import com.brandtube.proto.dto.response.config.APIResponseUtil;
import com.brandtube.proto.exceptions.CustomExceptions;
import com.brandtube.proto.exceptions.ServerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<APIResponse<Void>> handleServerException(ServerException ex) {
        return APIResponseUtil.internalServerError(null, ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<APIResponse<Void>> handleCustomException(CustomExceptions ex) {
        return APIResponseUtil.badRequest(null, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<ValidErrorResponse>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        ValidErrorResponse errorResponse = new ValidErrorResponse();
        errorResponse.setErrors(errors);
        return APIResponseUtil.badRequest(errorResponse);
    }

}
