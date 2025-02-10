package com.epam.pratsko.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.epam.pratsko.exception.ExceptionConstants.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class SongExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("errorCode", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SongValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleSongValidationExceptions(SongValidationException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(Map.of(
                        "error message", e.getMessage(),
                        "errorCode", HttpStatus.BAD_REQUEST.value())
                );
    }

    @ExceptionHandler(SongExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> handleSongExistsException(SongExistsException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "error message", e.getMessage(),
                        "errorCode", HttpStatus.CONFLICT.value())
                );
    }

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleSongNotFoundException(SongNotFoundException e) {
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
