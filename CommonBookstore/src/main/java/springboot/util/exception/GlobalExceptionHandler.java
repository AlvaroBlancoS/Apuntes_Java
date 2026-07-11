package springboot.util.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        FieldError fieldError = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .orElse(null);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);
        response.put("error", "Bad Request");
        response.put("code", "VALIDATION_ERROR");

        if (fieldError != null) {
            response.put("message", fieldError.getDefaultMessage());
            response.put("field", fieldError.getField());
            response.put("rejectedValue", fieldError.getRejectedValue());
        }

        response.put("path", request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(
            CustomException ex,
            HttpServletRequest request) {

        MessageException errorCode = ex.getMessageException();

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", errorCode.getStatus().value());
        response.put("error", errorCode.getStatus().getReasonPhrase());
        response.put("code", errorCode.getCode());
        response.put("message", errorCode.getMessage());
        response.put("path", request.getRequestURI());

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(response);
    }

}
