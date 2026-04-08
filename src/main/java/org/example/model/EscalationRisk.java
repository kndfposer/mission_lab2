package org.example.model;

public enum EscalationRisk {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL,
    UNKNOWN;

    public static EscalationRisk fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return EscalationRisk.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return UNKNOWN;
        }
    }
}
