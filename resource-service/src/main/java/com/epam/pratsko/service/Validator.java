package com.epam.pratsko.service;

import com.epam.pratsko.exception.ResourceValidationException;

import java.util.List;

import static com.epam.pratsko.exception.ErrorMessages.INVALID_ID;

public class Validator {

    public static long validateAndCastResourceId(String resourceId) {
        long id = Long.parseLong(resourceId);
        if (id <= 0) {
            throw new NumberFormatException(INVALID_ID);
        }

        return id;
    }

    public static List<Long> validateListResourceIds(List<String> resourceId) {
        List<Long> validatedIds = resourceId.stream()
                .map(Validator::validateAndCastResourceId)
                .toList();
        StringBuilder sb = new StringBuilder();
        validatedIds.forEach(sb::append);
        if ((sb.length() + resourceId.size() - 1) > 200) {
            throw new ResourceValidationException("The number of resources to delete should not exceed 200.");
        }

        return validatedIds;
    }
}
