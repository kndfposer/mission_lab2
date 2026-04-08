package org.example.loader.impl;

import org.example.loader.parsers.TypeMissionParser;
import org.example.loader.spi.MissionLoader;
import org.example.loader.support.FileContentUtils;
import org.example.loader.support.MissionFormatSniffer;
import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public class TypeMissionLoader implements MissionLoader {
    private final TypeMissionParser parser = new TypeMissionParser();
    public String getFormatName() { return "type"; }
    public boolean supports(File file) {
        if (MissionFormatSniffer.detect(file).equals("type")) return true;
        return switch ("type") {
            case "json" -> FileContentUtils.hasExtension(file, ".json");
            case "xml" -> FileContentUtils.hasExtension(file, ".xml");
            case "yaml" -> FileContentUtils.hasExtension(file, ".yaml") || FileContentUtils.hasExtension(file, ".yml");
            case "txt" -> FileContentUtils.hasExtension(file, ".txt");
            case "type" -> false;
            default -> false;
        };
    }
    public Mission load(File file) throws IOException { return parser.parse(file); }
}
