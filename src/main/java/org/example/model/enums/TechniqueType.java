package org.example.model.enums;

public enum TechniqueType {
    INNATE, SHIKIGAMI, BODY, WEAPON, BARRIER, SUPPORT, UNKNOWN;

    public static TechniqueType fromString(String value) {
        if (value == null || value.isBlank()) return UNKNOWN;
        try { return valueOf(value.trim().toUpperCase()); }
        catch (Exception e) { return UNKNOWN; }
    }
}