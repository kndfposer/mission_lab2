package org.example.model;

public enum SorcererRank {
    GRADE_4,
    GRADE_3,
    GRADE_2,
    GRADE_1,
    SEMI_GRADE_1,
    SPECIAL_GRADE,
    UNKNOWN;

    public static SorcererRank fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return SorcererRank.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return UNKNOWN;
        }
    }
}
