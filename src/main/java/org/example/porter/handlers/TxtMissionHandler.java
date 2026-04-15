package org.example.porter.handlers;

import org.example.porter.parsers.TxtMissionParser;
import org.example.porter.handler_interface.MissionLoader;
import org.example.porter.fabric_p.FileContentUtils;
import org.example.porter.fabric_p.MissionFormatSniffer;
import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public class TxtMissionHandler implements MissionLoader {
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

