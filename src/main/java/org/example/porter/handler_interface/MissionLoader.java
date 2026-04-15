package org.example.porter.handler_interface;

import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public interface MissionLoader extends MissionFormatSupport {
    Mission load(File file) throws IOException;
}
