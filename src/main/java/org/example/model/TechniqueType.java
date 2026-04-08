package org.example.model;

public enum TechniqueType {
    INNATE,
    SHIKIGAMI,
    WEAPON,
    BODY,
    BARRIER,
    SUPPORT,
    UNKNOWN;

    public static TechniqueType fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return TechniqueType.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return UNKNOWN;
        }
    }
}
