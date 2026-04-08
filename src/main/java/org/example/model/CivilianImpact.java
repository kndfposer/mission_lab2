package org.example.model;

import org.example.model.enums.PublicExposureRisk;

public class CivilianImpact {
    private Integer evacuated;
    private Integer injured;
    private Integer missing;
    private PublicExposureRisk publicExposureRisk = PublicExposureRisk.UNKNOWN;

    public Integer getEvacuated() { return evacuated; }
    public void setEvacuated(Integer evacuated) { this.evacuated = evacuated; }
    public Integer getInjured() { return injured; }
    public void setInjured(Integer injured) { this.injured = injured; }
    public Integer getMissing() { return missing; }
    public void setMissing(Integer missing) { this.missing = missing; }
    public PublicExposureRisk getPublicExposureRisk() { return publicExposureRisk; }
    public void setPublicExposureRisk(PublicExposureRisk publicExposureRisk) { this.publicExposureRisk = publicExposureRisk; }
}
