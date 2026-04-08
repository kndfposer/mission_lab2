package org.example.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.builder.DefaultMissionBuilder;
import org.example.builder.MissionBuilder;
import org.example.loader.parsers.raw.MissionRaw;
import org.example.model.Mission;
import org.example.util.MissionValidator;

import java.io.File;
import java.io.IOException;

public abstract class AbstractJacksonMissionLoader implements MissionLoader {
    private final ObjectMapper objectMapper;
    private final MissionDataAdapter<MissionRaw> adapter;

    protected AbstractJacksonMissionLoader(ObjectMapper objectMapper, MissionDataAdapter<MissionRaw> adapter) {
        this.objectMapper = objectMapper;
        this.adapter = adapter;
    }

    protected abstract Class<MissionRaw> rawType();

    @Override
    public Mission load(File file) throws IOException {
        MissionRaw raw = objectMapper.readValue(file, rawType());
        MissionBuilder builder = new DefaultMissionBuilder();
        adapter.fill(raw, builder);
        Mission mission = builder.build();
        MissionValidator.validate(mission);
        return mission;
    }
}
