package org.example.util;

import java.io.File;

public final class MissionFormatSniffer {
    private MissionFormatSniffer() {
    }

    public static boolean looksLikeJson(File file) {
        try {
            String text = FileContentUtils.readTrimmed(file);
            return FileContentUtils.hasExtension(file, ".json")
                    || text.startsWith("{")
                    || text.startsWith("[");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean looksLikeXml(File file) {
        try {
            String text = FileContentUtils.readTrimmed(file);
            return FileContentUtils.hasExtension(file, ".xml")
                    || text.startsWith("<");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean looksLikeYaml(File file) {
        try {
            if (FileContentUtils.hasExtension(file, ".yaml") || FileContentUtils.hasExtension(file, ".yml")) {
                return true;
            }
            String first = FileContentUtils.firstNonEmptyLine(file);
            String text = FileContentUtils.readTrimmed(file);
            return !text.startsWith("{")
                    && !text.startsWith("[")
                    && !text.startsWith("<")
                    && first.contains(":")
                    && !first.startsWith("[")
                    && !first.contains("|");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean looksLikeSectionText(File file) {
        try {
            if (FileContentUtils.hasExtension(file, ".txt")) {
                String first = FileContentUtils.firstNonEmptyLine(file);
                if (first.startsWith("MISSION_CREATED|")) {
                    return false;
                }
                return true;
            }
            String first = FileContentUtils.firstNonEmptyLine(file);
            return first.startsWith("[MISSION]")
                    || first.startsWith("[CURSE]")
                    || first.startsWith("[SORCERER]")
                    || first.startsWith("[TECHNIQUE]")
                    || first.startsWith("[ENVIRONMENT]")
                    || first.startsWith("[ENVIRONMENT_CONDITIONS]");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean looksLikeTypeEvent(File file) {
        try {
            if (FileContentUtils.hasExtension(file, ".type")) {
                return true;
            }
            String first = FileContentUtils.firstNonEmptyLine(file);
            return first.startsWith("MISSION_CREATED|")
                    || first.startsWith("CURSE_DETECTED|")
                    || first.startsWith("MISSION_RESULT|")
                    || first.startsWith("TIMELINE_EVENT|")
                    || first.startsWith("ENEMY_ACTION|")
                    || first.startsWith("CIVILIAN_IMPACT|");
        } catch (Exception e) {
            return false;
        }
    }
}

