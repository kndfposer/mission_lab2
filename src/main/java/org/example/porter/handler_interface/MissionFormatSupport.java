package org.example.porter.handler_interface;

import java.io.File;

public interface MissionFormatSupport {
    String getFormatName();
    boolean supports(File file);
}

