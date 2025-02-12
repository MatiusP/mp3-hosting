package com.epam.pratsko.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;

@JsonPropertyOrder({"errorMessage", "errorCode", "details"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ResourceErrorResponse(String errorMessage, String errorCode, Map<String, String> details) {
}
