package org.example.loader.parsers.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CivilianImpactRaw {
    private Integer evacuated;
    private Integer injured;
    private Integer missing;
    private String publicExposureRisk;

    public Integer getEvacuated() { return evacuated; }
    public void setEvacuated(Integer evacuated) { this.evacuated = evacuated; }
    public Integer getInjured() { return injured; }
    public void setInjured(Integer injured) { this.injured = injured; }
    public Integer getMissing() { return missing; }
    public void setMissing(Integer missing) { this.missing = missing; }
    public String getPublicExposureRisk() { return publicExposureRisk; }
    public void setPublicExposureRisk(String publicExposureRisk) { this.publicExposureRisk = publicExposureRisk; }
}
