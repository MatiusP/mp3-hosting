package com.epam.pratsko.service;

import com.epam.pratsko.exception.SongValidationException;

import java.util.List;

import static com.epam.pratsko.exception.ErrorMessages.INVALID_CSV_LENGTH_ERROR;
import static com.epam.pratsko.exception.ErrorMessages.INVALID_ID_ERROR;

public class SongValidator {

    private SongValidator() {
    }

    public static long validateAndCastSongId(String songId) {
        try {
            long id = Long.parseLong(songId);
            if (id <= 0) {
                throw new NumberFormatException();
            }

            return id;
        } catch (NumberFormatException e) {
            throw new SongValidationException(INVALID_ID_ERROR);
        }
    }

    public static List<Long> validateListSongsIds(List<String> songIds) {
        List<Long> validatedIds = songIds.stream()
                .map(SongValidator::validateAndCastSongId)
                .toList();
        StringBuilder sb = new StringBuilder();
        validatedIds.forEach(sb::append);
        if ((sb.length() + songIds.size() - 1) > 200) {
            throw new SongValidationException(INVALID_CSV_LENGTH_ERROR);
        }

        return validatedIds;
    }
}
