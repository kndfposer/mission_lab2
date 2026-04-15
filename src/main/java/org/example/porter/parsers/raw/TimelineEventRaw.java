package org.example.porter.parsers.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimelineEventRaw { public String timestamp;
    public String type;
    public String description;
}
