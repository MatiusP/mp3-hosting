package com.epam.pratsko.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static com.epam.pratsko.exception.ErrorMessages.GENERAL_VALIDATION_ERROR;
import static com.epam.pratsko.exception.ErrorMessages.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceParseException.class)
    public ResponseEntity<ResourceErrorResponse> handleResourceParseException(ResourceParseException e) {
        log.error(e.getMessage(), e);
        Map<String, String> details = Map.of("error cause", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResourceErrorResponse(GENERAL_VALIDATION_ERROR, String.valueOf(HttpStatus.BAD_REQUEST.value()), details));
    }

    @ExceptionHandler(ResourceValidationException.class)
    public ResponseEntity<ResourceErrorResponse> handleValidationException(ResourceValidationException e) {
        log.error(e.getMessage(), e);
        Map<String, String> details = Map.of("error cause", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResourceErrorResponse(GENERAL_VALIDATION_ERROR, String.valueOf(HttpStatus.BAD_REQUEST.value()), details));
    }

    @ExceptionHandler(ProcessResourceException.class)
    public ResponseEntity<ResourceErrorResponse> handleProcessResourceException(ProcessResourceException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResourceErrorResponse(e.getMessage(), String.valueOf(HttpStatus.NOT_FOUND.value()), Map.of()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResourceErrorResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResourceErrorResponse(INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), Map.of()));
    }
}
