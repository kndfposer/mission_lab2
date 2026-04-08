package org.example.loader.support;

import java.io.File;
import java.io.IOException;

public final class MissionFormatSniffer {
    private MissionFormatSniffer() {}
    public static String detect(File file) {
        try {
            String first = FileContentUtils.firstNonBlankLine(file);
            if (first.startsWith("{")) return "json";
            if (first.startsWith("<")) return "xml";
            if (first.startsWith("[MISSION]")) return "txt";
            if (first.startsWith("MISSION_CREATED|") || first.startsWith("CURSE_DETECTED|")) return "type";
            String content = FileContentUtils.readAll(file);
            if (content.contains("missionId:") && content.contains("curse:")) return "yaml";
        } catch (IOException ignored) {}
        return "unknown";
    }
}
