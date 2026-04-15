package org.example.porter.parsers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.porter.parsers.raw.MissionRaw;
import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public class XmlMissionParser {
    private final XmlMapper mapper = (XmlMapper) new XmlMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    public Mission parse(File file) throws IOException { return RawMissionMapper.map(mapper.readValue(file, MissionRaw.class)); }
}

