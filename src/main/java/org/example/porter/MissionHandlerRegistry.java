package org.example.porter;

import org.example.porter.handler_interface.MissionLoader;
import org.example.model.Mission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MissionHandlerRegistry {
    private final List<MissionLoader> loaders = new ArrayList<>();
    public MissionHandlerRegistry register(MissionLoader loader) {
        loaders.add(loader);
        return this;
    }
    public Mission load(File file) throws IOException {
        if (!file.exists()) throw new IOException("Файл не найден");
        for (MissionLoader loader : loaders) if (loader.supports(file)) return loader.load(file);
        throw new IOException("Не удалось определить формат файла");
    }
}
