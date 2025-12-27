package com.brandtube.proto.dto.response.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class APIResponseUtil {

    private static <C> APIResponse<C> constructAPIResponse(HttpStatus status, C data, String message) {
          return APIResponse.<C>builder()
                .code(status)
                .data(data)
                .message(message)
                .build();
    }

    private static <C> ResponseEntity<APIResponse<C>> constructResponseEntity(HttpStatus status, C data) {
        return ResponseEntity.status(status).body(constructAPIResponse(status, data, status.getReasonPhrase()));
    }

    private static <C> ResponseEntity<APIResponse<C>> constructResponseEntity(HttpStatus status, C data, String message) {
        return ResponseEntity.status(status).body(constructAPIResponse(status, data, message));
    }

    public static <C> ResponseEntity<APIResponse<C>> ok(C data) {
        return constructResponseEntity(HttpStatus.OK, data);
    }

    public static <C> ResponseEntity<APIResponse<C>> ok(C data, String message) {
        return constructResponseEntity(HttpStatus.OK, data, message);
    }

    public static <C> ResponseEntity<APIResponse<C>> created(C data) {
        return constructResponseEntity(HttpStatus.CREATED, data);
    }

    public static <C> ResponseEntity<APIResponse<C>> created(C data, String message) {
        return constructResponseEntity(HttpStatus.CREATED, data, message);
    }

    public static <C> ResponseEntity<APIResponse<C>> unauthorized(C data) {
        return constructResponseEntity(HttpStatus.UNAUTHORIZED, data);
    }

    public static <C> ResponseEntity<APIResponse<C>> unauthorized(C data, String message) {
        return constructResponseEntity(HttpStatus.UNAUTHORIZED, data, message);
    }

    public static <C> ResponseEntity<APIResponse<C>> forbidden(C data) {
        return constructResponseEntity(HttpStatus.FORBIDDEN, data);
    }

    public static <C> ResponseEntity<APIResponse<C>> forbidden(C data, String message) {
        return constructResponseEntity(HttpStatus.FORBIDDEN, data, message);
    }

}
