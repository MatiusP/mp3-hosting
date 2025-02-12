package com.epam.pratsko.exception;

public class ErrorMessages {

    public static final String GENERAL_VALIDATION_ERROR = "Validation error.";
    public static final String SONG_EXISTS_ERROR = "Song with id=%s already exists.";
    public static final String INVALID_ID_ERROR = "Provided invalid ID. Only numeric values greater than zero are allowed.";
    public static final String SONG_NOT_FOUND_ERROR = "The song with id=%s not found.";
    public static final String INVALID_CSV_LENGTH_ERROR = "The CSV string length should not exceed 200 characters.";
    public static final String INTERNAL_SERVER_ERROR = "Sorry, something went wrong. We are working on it.";

    private ErrorMessages() {
    }
}
