package org.example.loader.parsers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.loader.parsers.raw.MissionRaw;
import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public class YamlMissionParser {
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory())
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public Mission parse(File file) throws IOException { return RawMissionMapper.map(mapper.readValue(file, MissionRaw.class)); }
}
