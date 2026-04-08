package org.example.loader;

import org.example.model.Mission;

import java.io.File;
import java.io.IOException;

public interface MissionLoader {
    String getFormatName();
    boolean supports(File file);
    Mission load(File file) throws IOException;
}
