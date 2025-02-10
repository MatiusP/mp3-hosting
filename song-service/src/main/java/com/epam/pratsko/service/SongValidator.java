package com.epam.pratsko.service;

import com.epam.pratsko.exception.SongValidationException;

import java.util.List;

import static com.epam.pratsko.exception.ExceptionConstants.INVALID_ID_ERROR;

public class SongValidator {

    public static List<Long> validateListSongsIds(List<String> songIds) {
        List<Long> validatedIds = songIds.stream()
                .map(SongValidator::validateAndCastSongId)
                .toList();
        StringBuilder sb = new StringBuilder();
        validatedIds.forEach(sb::append);
        if ((sb.length() + songIds.size() - 1) > 200) {
            throw new SongValidationException("The number of songs to delete should not exceed 200.");
        }
        return validatedIds;
    }

    public static long validateAndCastSongId(String songId) {
        long id = Long.parseLong(songId);
        if (id <= 0) {
            throw new NumberFormatException(INVALID_ID_ERROR);
        }
        return id;
    }
}
