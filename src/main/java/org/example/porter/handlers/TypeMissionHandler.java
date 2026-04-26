package org.example.porter.handlers;

import org.example.porter.parsers.TypeMissionParser;
import org.example.porter.handler_interface.MissionLoader;
import org.example.porter.fabric_p.FileContentUtils;
import org.example.porter.fabric_p.MissionFormatSniffer;
import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public class TypeMissionHandler implements MissionLoader {
    private final TypeMissionParser parser = new TypeMissionParser();
    public String getFormatName() { return "type"; }
    public boolean supports(File file) {
        if (MissionFormatSniffer.detect(file).equals("type")) return true;
        return FileContentUtils.hasExtension(file,".type");
        }
    public Mission load(File file) throws IOException { return parser.parse(file); }
}
