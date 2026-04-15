package org.example.porter;

import org.example.porter.handlers.*;

public final class DefHandlerRegistryFactory {
    private DefHandlerRegistryFactory() {}
    public static MissionHandlerRegistry createDefault() {
        return new MissionHandlerRegistry()
                .register(new JsonMissionHandler())
                .register(new XmlMissionHandler())
                .register(new YamlMissionHandler())
                .register(new TxtMissionHandler())
                .register(new TypeMissionHandler());
    }
}
