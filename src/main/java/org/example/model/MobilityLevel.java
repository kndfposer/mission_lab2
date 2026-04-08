package org.example.model;

public enum MobilityLevel {
    LOW,
    MEDIUM,
    HIGH,
    UNKNOWN;

    public static MobilityLevel fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return MobilityLevel.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return UNKNOWN;
        }
    }
}
