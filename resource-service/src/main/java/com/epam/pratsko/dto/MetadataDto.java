package com.epam.pratsko.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class MetadataDto {
    private Map<String, String> metadata;

}
