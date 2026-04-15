package org.example.porter.handlers;

import org.example.porter.parsers.YamlMissionParser;
import org.example.porter.handler_interface.MissionLoader;
import org.example.porter.fabric_p.FileContentUtils;
import org.example.porter.fabric_p.MissionFormatSniffer;
import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public class YamlMissionHandler implements MissionLoader {
    private final YamlMissionParser parser = new YamlMissionParser();
    public String getFormatName() { return "yaml"; }
    public boolean supports(File file) {
        if (MissionFormatSniffer.detect(file).equals("yaml")) return true;
        return switch ("yaml") {
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
