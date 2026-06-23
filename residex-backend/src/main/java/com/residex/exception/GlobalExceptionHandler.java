package com.residex.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<ApiError>
    handleResourceNotFound(
            ResourceNotFoundException ex
    ) {

        ApiError error =
                ApiError.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .timestamp(
                                LocalDateTime.now()
                        )
                        .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError>
    handleException(
            Exception ex
    ) {

        ApiError error =
                ApiError.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .timestamp(
                                LocalDateTime.now()
                        )
                        .build();

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(error);
    }

    @ExceptionHandler(
                MethodArgumentNotValidException.class
        )
        public ResponseEntity<ApiError>
        handleValidation(
                MethodArgumentNotValidException ex
        ) {

        String message =
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        ApiError error =
                ApiError.builder()
                        .success(false)
                        .message(message)
                        .timestamp(
                                LocalDateTime.now()
                        )
                        .build();

        return ResponseEntity
                .badRequest()
                .body(error);
        }
}