package org.example.model.enums;

public enum ThreatLevel {
    LOW, MEDIUM, HIGH, SPECIAL_GRADE, UNKNOWN;

    public static ThreatLevel fromString(String value) {
        if (value == null || value.isBlank()) return UNKNOWN;
        try { return valueOf(value.trim().toUpperCase()); }
        catch (Exception e) { return UNKNOWN; }
    }
}
