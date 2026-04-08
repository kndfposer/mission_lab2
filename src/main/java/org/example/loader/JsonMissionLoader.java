package org.example.loader;

import org.example.loader.parsers.raw.MissionRaw;
import org.example.util.MissionFormatSniffer;
import org.example.util.ObjectMapperFactory;

import java.io.File;

public class JsonMissionLoader extends AbstractJacksonMissionLoader {
    public JsonMissionLoader() {
        super(ObjectMapperFactory.jsonMapper(), new JsonLikeMissionAdapter());
    }

    @Override
    protected Class<MissionRaw> rawType() {
        return MissionRaw.class;
    }

    @Override
    public String getFormatName() {
        return "JSON";
    }

    @Override
    public boolean supports(File file) {
        return MissionFormatSniffer.looksLikeJson(file);
    }
}
