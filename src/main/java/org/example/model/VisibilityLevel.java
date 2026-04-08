package org.example.model;

public enum VisibilityLevel {
    LOW,
    MEDIUM,
    HIGH,
    UNKNOWN;

    public static VisibilityLevel fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return VisibilityLevel.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return UNKNOWN;
        }
    }
}
