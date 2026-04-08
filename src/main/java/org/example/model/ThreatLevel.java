package org.example.model;

public enum ThreatLevel {
    LOW,
    MEDIUM,
    HIGH,
    SPECIAL_GRADE,
    UNKNOWN;

    public static ThreatLevel fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return ThreatLevel.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return UNKNOWN;
        }
    }
}