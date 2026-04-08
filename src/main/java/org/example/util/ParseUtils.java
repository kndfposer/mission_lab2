package org.example.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public final class ParseUtils {
    private ParseUtils() {
    }

    public static String clean(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    public static LocalDate parseDate(String value) {
        String cleaned = clean(value);
        if (cleaned == null) {
            return null;
        }
        return LocalDate.parse(cleaned);
    }

    public static LocalDateTime parseDateTime(String value) {
        String cleaned = clean(value);
        if (cleaned == null) {
            return null;
        }
        try {
            return LocalDateTime.parse(cleaned);
        } catch (DateTimeParseException ex) {
            return LocalDateTime.parse(cleaned, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }

    public static Long parseLong(String value) {
        String cleaned = clean(value);
        if (cleaned == null) {
            return null;
        }
        return Long.parseLong(cleaned);
    }

    public static Integer parseInteger(String value) {
        String cleaned = clean(value);
        if (cleaned == null) {
            return null;
        }
        return Integer.parseInt(cleaned);
    }

    public static Boolean parseBoolean(String value) {
        String cleaned = clean(value);
        if (cleaned == null) {
            return null;
        }
        return Boolean.parseBoolean(cleaned);
    }

    public static List<String> normalizeList(List<String> values) {
        List<String> result = new ArrayList<>();
        if (values == null) {
            return result;
        }
        for (String value : values) {
            String cleaned = clean(value);
            if (cleaned != null) {
                result.add(cleaned);
            }
        }
        return result;
    }
}
