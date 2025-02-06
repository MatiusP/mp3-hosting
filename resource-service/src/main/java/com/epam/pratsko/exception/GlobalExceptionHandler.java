package com.epam.pratsko.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static com.epam.pratsko.exception.ExceptionConstants.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceParseException.class)
    public ResponseEntity<?> handleResourceParseException(ResourceParseException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(Map.of(
                        "error message", e.getMessage(),
                        "errorCode", HttpStatus.BAD_REQUEST.value())
                );
    }

    @ExceptionHandler(ResourceValidationException.class)
    public ResponseEntity<?> handleValidationException(ResourceValidationException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(Map.of(
                        "error message", e.getMessage(),
                        "errorCode", HttpStatus.BAD_REQUEST.value())
                );
    }

    @ExceptionHandler(ProcessResourceException.class)
    public ResponseEntity<?> handleProcessResourceException(ProcessResourceException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "error message", e.getMessage(),
                        "errorCode", HttpStatus.NOT_FOUND.value())
                );
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleNumberFormatException(NumberFormatException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(Map.of(
                        "error message", e.getMessage(),
                        "errorCode", HttpStatus.BAD_REQUEST.value())
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error message", INTERNAL_SERVER_ERROR,
                        "errorCode", HttpStatus.BAD_REQUEST.value())
                );
    }
}
