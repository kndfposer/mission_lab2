package org.example.model.enums;

public enum EscalationRisk {
    LOW, MEDIUM, HIGH, UNKNOWN;

    public static EscalationRisk fromString(String value) {
        if (value == null || value.isBlank()) return UNKNOWN;
        try { return valueOf(value.trim().toUpperCase()); }
        catch (Exception e) { return UNKNOWN; }
    }
}
