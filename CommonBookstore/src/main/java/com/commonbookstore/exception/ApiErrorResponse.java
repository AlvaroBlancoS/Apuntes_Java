package com.commonbookstore.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String code;
    private String message;
    private String field;
    private Object rejectedValue;
    private String path;
}
