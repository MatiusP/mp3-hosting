package com.epam.pratsko.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.epam.pratsko.exception.ErrorMessages.GENERAL_VALIDATION_ERROR;
import static com.epam.pratsko.exception.ErrorMessages.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class SongExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SongErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);

        Map<String, String> details = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            details.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new SongErrorResponse(GENERAL_VALIDATION_ERROR, String.valueOf(HttpStatus.BAD_REQUEST.value()), details));
    }

    @ExceptionHandler(SongValidationException.class)
    public ResponseEntity<SongErrorResponse> handleSongValidationExceptions(SongValidationException e) {
        log.error(e.getMessage(), e);
        Map<String, String> details = Map.of("error cause", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new SongErrorResponse(GENERAL_VALIDATION_ERROR, String.valueOf(HttpStatus.BAD_REQUEST.value()), details));
    }

    @ExceptionHandler(SongExistsException.class)
    public ResponseEntity<SongErrorResponse> handleSongExistsException(SongExistsException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new SongErrorResponse(e.getMessage(), String.valueOf(HttpStatus.CONFLICT.value()), Map.of()));
    }

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<SongErrorResponse> handleSongNotFoundException(SongNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new SongErrorResponse(e.getMessage(), String.valueOf(HttpStatus.NOT_FOUND.value()), Map.of()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SongErrorResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new SongErrorResponse(INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.BAD_REQUEST.value()), Map.of()));
    }

}
