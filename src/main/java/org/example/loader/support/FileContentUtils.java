package org.example.loader.support;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public final class FileContentUtils {
    private FileContentUtils() {}
    public static String readAll(File file) throws IOException { return Files.readString(file.toPath(), StandardCharsets.UTF_8); }
    public static String firstNonBlankLine(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        for (String line : lines) if (line != null && !line.trim().isEmpty()) return line.trim();
        return "";
    }
    public static boolean hasExtension(File file, String ext) { return file.getName().toLowerCase().endsWith(ext.toLowerCase()); }
}
