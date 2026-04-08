package org.example.loader;

import org.example.loader.parsers.raw.MissionRaw;
import org.example.util.MissionFormatSniffer;
import org.example.util.ObjectMapperFactory;

import java.io.File;

public class YamlMissionLoader extends AbstractJacksonMissionLoader {
    public YamlMissionLoader() {
        super(ObjectMapperFactory.yamlMapper(), new JsonLikeMissionAdapter());
    }

    @Override
    protected Class<MissionRaw> rawType() {
        return MissionRaw.class;
    }

    @Override
    public String getFormatName() {
        return "YAML";
    }

    @Override
    public boolean supports(File file) {
        return MissionFormatSniffer.looksLikeYaml(file);
    }
}
