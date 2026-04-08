package org.example.model;

public enum Outcome {
    SUCCESS,
    PARTIAL_SUCCESS,
    FAILURE,
    UNKNOWN;

    public static Outcome fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Outcome.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return UNKNOWN;
        }
    }
}
