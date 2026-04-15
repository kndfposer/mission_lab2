package org.example.porter.handlers;

import org.example.porter.parsers.JsonMissionParser;
import org.example.porter.handler_interface.MissionLoader;
import org.example.porter.fabric_p.FileContentUtils;
import org.example.porter.fabric_p.MissionFormatSniffer;
import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public class JsonMissionHandler implements MissionLoader {
    private final JsonMissionParser parser = new JsonMissionParser();
    public String getFormatName() { return "json"; }
    public boolean supports(File file) {
        if (MissionFormatSniffer.detect(file).equals("json")) return true;
        return switch ("json") {
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
