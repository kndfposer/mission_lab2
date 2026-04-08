package org.example.model.enums;

public enum MissionOutcome {
    SUCCESS, PARTIAL_SUCCESS, FAILURE, UNKNOWN;

    public static MissionOutcome fromString(String value) {
        if (value == null || value.isBlank()) return UNKNOWN;
        try { return valueOf(value.trim().toUpperCase()); }
        catch (Exception e) { return UNKNOWN; }
    }
}
