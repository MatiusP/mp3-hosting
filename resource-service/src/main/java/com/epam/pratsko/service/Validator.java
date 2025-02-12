package com.epam.pratsko.service;

import com.epam.pratsko.exception.ResourceValidationException;

import java.util.List;

import static com.epam.pratsko.exception.ErrorMessages.INVALID_CSV_LENGTH_ERROR;
import static com.epam.pratsko.exception.ErrorMessages.INVALID_ID_ERROR;

public class Validator {

    private Validator() {
    }

    public static long validateAndCastResourceId(String resourceId) {
        try {
            long id = Long.parseLong(resourceId);
            if (id <= 0) {
                throw new NumberFormatException();
            }

            return id;
        } catch (NumberFormatException e) {
            throw new ResourceValidationException(INVALID_ID_ERROR);
        }
    }

    public static List<Long> validateListResourceIds(List<String> resourceIds) {
        List<Long> validatedIds = resourceIds.stream()
                .map(Validator::validateAndCastResourceId)
                .toList();
        StringBuilder sb = new StringBuilder();
        validatedIds.forEach(sb::append);
        if ((sb.length() + resourceIds.size() - 1) > 200) {
            throw new ResourceValidationException(INVALID_CSV_LENGTH_ERROR);
        }

        return validatedIds;
    }
}
