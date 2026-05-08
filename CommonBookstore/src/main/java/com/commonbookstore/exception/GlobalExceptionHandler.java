package com.commonbookstore.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        FieldError fieldError = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .orElse(null);

        ApiErrorResponse.ApiErrorResponseBuilder response = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Bad Request")
                .code("VALIDATION_ERROR")
                .path(request.getRequestURI());

        if (fieldError != null) {
            response.message(fieldError.getDefaultMessage())
                    .field(fieldError.getField())
                    .rejectedValue(fieldError.getRejectedValue());
        }

        return ResponseEntity.badRequest().body(response.build());
    }
}
