package org.example.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public final class FileContentUtils {
    private FileContentUtils() {
    }

    public static String readTrimmed(File file) throws IOException {
        return Files.readString(file.toPath(), StandardCharsets.UTF_8).trim();
    }

    public static List<String> readLines(File file) throws IOException {
        return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
    }

    public static String firstNonEmptyLine(File file) throws IOException {
        for (String line : readLines(file)) {
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                return trimmed;
            }
        }
        return "";
    }

    public static boolean hasExtension(File file, String ext) {
        return file.getName().toLowerCase().endsWith(ext.toLowerCase());
    }
}
