package org.example.loader.impl;

import org.example.loader.parsers.TxtMissionParser;
import org.example.loader.spi.MissionLoader;
import org.example.loader.support.FileContentUtils;
import org.example.loader.support.MissionFormatSniffer;
import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public class TxtMissionLoader implements MissionLoader {
    private final TxtMissionParser parser = new TxtMissionParser();
    public String getFormatName() { return "txt"; }
    public boolean supports(File file) {
        if (MissionFormatSniffer.detect(file).equals("txt")) return true;
        return switch ("txt") {
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

