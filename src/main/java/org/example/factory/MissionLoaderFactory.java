package org.example.factory;

import org.example.loader.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MissionLoaderFactory {
    private final List<MissionLoader> loaders = new ArrayList<>();

    public MissionLoaderFactory() {
        register(new JsonMissionLoader());
        register(new YamlMissionLoader());
        register(new XmlMissionLoader());
        register(new TxtMissionLoader());
        register(new TypeMissionLoader());
    }

    public void register(MissionLoader loader) {
        loaders.add(loader);
    }

    public MissionLoader getLoader(File file) {
        return loaders.stream()
                .filter(loader -> loader.supports(file))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Не удалось определить формат файла: " + file.getName()));
    }
}
