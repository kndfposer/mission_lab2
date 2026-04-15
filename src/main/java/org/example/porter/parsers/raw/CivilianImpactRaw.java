package org.example.porter.parsers.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CivilianImpactRaw { public Integer evacuated;
    public Integer injured;
    public Integer missing;
    public String publicExposureRisk;
}

