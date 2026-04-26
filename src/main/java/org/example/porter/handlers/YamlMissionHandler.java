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
        return  FileContentUtils.hasExtension(file, ".yaml") || FileContentUtils.hasExtension(file, ".yml");
    }
    public Mission load(File file) throws IOException { return parser.parse(file); }
}
