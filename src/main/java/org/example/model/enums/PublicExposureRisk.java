package org.example.model.enums;

public enum PublicExposureRisk {
    LOW, MEDIUM, HIGH, UNKNOWN;

    public static PublicExposureRisk fromString(String value) {
        if (value == null || value.isBlank()) return UNKNOWN;
        try { return valueOf(value.trim().toUpperCase()); }
        catch (Exception e) { return UNKNOWN; }
    }
}
