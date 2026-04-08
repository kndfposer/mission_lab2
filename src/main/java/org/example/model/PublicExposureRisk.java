package org.example.model;

public enum PublicExposureRisk {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL,
    UNKNOWN;

    public static PublicExposureRisk fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return PublicExposureRisk.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return UNKNOWN;
        }
    }
}
