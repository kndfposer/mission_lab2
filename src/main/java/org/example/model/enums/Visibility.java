package org.example.model.enums;

public enum Visibility {
    LOW, MEDIUM, HIGH, UNKNOWN;

    public static Visibility fromString(String value) {
        if (value == null || value.isBlank()) return UNKNOWN;
        try { return valueOf(value.trim().toUpperCase()); }
        catch (Exception e) { return UNKNOWN; }
    }
}

