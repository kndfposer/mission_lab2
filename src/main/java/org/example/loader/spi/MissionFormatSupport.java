package org.example.loader.spi;

import java.io.File;

public interface MissionFormatSupport {
    String getFormatName();
    boolean supports(File file);
}

