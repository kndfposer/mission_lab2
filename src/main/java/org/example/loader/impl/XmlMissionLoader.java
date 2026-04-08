package org.example.loader.impl;

import org.example.loader.parsers.XmlMissionParser;
import org.example.loader.spi.MissionLoader;
import org.example.loader.support.FileContentUtils;
import org.example.loader.support.MissionFormatSniffer;
import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public class XmlMissionLoader implements MissionLoader {
    private final XmlMissionParser parser = new XmlMissionParser();
    public String getFormatName() { return "xml"; }
    public boolean supports(File file) {
        if (MissionFormatSniffer.detect(file).equals("xml")) return true;
        return switch ("xml") {
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
