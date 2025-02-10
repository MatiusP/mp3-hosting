package com.epam.pratsko.exception;

public class ExceptionConstants {

    public static final String SONG_EXISTS_ERROR = "Song with id %s already exists.";
    public static final String INVALID_ID_ERROR = "The provided ID is invalid.";
    public static final String SONG_NOT_FOUND_ERROR = "Song not found by id %s";
    public static final String INTERNAL_SERVER_ERROR = "Something went wrong.";


    private ExceptionConstants() {
    }
}
