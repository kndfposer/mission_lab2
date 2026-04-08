package org.example.loader;

import org.example.loader.parsers.raw.MissionRaw;
import org.example.util.MissionFormatSniffer;
import org.example.util.ObjectMapperFactory;

import java.io.File;

public class XmlMissionLoader extends AbstractJacksonMissionLoader {
    public XmlMissionLoader() {
        super(ObjectMapperFactory.xmlMapper(), new JsonLikeMissionAdapter());
    }

    @Override
    protected Class<MissionRaw> rawType() {
        return MissionRaw.class;
    }

    @Override
    public String getFormatName() {
        return "XML";
    }

    @Override
    public boolean supports(File file) {
        return MissionFormatSniffer.looksLikeXml(file);
    }
}
