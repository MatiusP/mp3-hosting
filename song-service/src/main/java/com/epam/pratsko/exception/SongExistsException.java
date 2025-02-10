package com.epam.pratsko.exception;

public class SongExistsException extends RuntimeException {

    public SongExistsException(String message) {
        super(message);
    }
}
