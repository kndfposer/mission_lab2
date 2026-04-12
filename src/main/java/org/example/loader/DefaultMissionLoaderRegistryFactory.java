package org.example.loader;

import org.example.loader.impl.*;

public final class DefaultMissionLoaderRegistryFactory {
    private DefaultMissionLoaderRegistryFactory() {}
    public static MissionLoaderRegistry createDefault() {
        return new MissionLoaderRegistry()
                .register(new JsonMissionLoader())
                .register(new XmlMissionLoader())
                .register(new YamlMissionLoader())
                .register(new TxtMissionLoader())
                .register(new TypeMissionLoader());
    }
}
