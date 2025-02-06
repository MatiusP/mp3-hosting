package com.epam.pratsko.service;

import com.epam.pratsko.exception.ResourceValidationException;

import java.util.List;

import static com.epam.pratsko.exception.ExceptionConstants.INVALID_ID;

public class Validator {

    public static long validateAndCastResourceId(String resourceId) {
        long id = Long.parseLong(resourceId);
        if (id <= 0) {
            throw new NumberFormatException(INVALID_ID);
        }

        return id;
    }

    public static List<Long> validateListResourceIds(List<String> resourceId) {
        if (resourceId.size() > 200) {
            throw new ResourceValidationException("The number of resources to delete should not exceed 200.");
        }

        return resourceId.stream()
                .map(Validator::validateAndCastResourceId)
                .toList();
    }
}
