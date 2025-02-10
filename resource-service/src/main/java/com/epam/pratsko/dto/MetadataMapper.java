package com.epam.pratsko.dto;

import org.apache.tika.metadata.Metadata;

import java.util.HashMap;
import java.util.Map;

public class MetadataMapper {

    public static MetadataDto toDto(Metadata metadata) {
        Map<String, String> map = new HashMap<>();
        for (String name : metadata.names()) {
            String parsedName = "";
            if (name.split(":").length > 1) {
                parsedName = name.split(":")[1];
            }
            if (name.contains("releaseDate")) {
                map.put("year", metadata.get(name));
            }
            if (name.contains("title")) {
                map.put("name", metadata.get(name));
            }
            if (name.contains("duration")) {
                map.put("duration", convertDuration(metadata.get(name)));
            } else {
                map.put(parsedName, metadata.get(name));
            }
        }
        return new MetadataDto(map);
    }

    private static String convertDuration(String duration) {
        double totalSecondsDouble = Double.parseDouble(duration);
        int totalSeconds = (int) Math.floor(totalSecondsDouble);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}
