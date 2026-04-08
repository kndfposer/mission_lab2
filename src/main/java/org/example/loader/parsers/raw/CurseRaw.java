package org.example.loader.parsers.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurseRaw {
    private String name;
    private String threatLevel;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getThreatLevel() { return threatLevel; }
    public void setThreatLevel(String threatLevel) { this.threatLevel = threatLevel; }
}
