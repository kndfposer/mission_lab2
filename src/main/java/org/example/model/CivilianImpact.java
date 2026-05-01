package org.example.model;

import jakarta.persistence.*;
import org.example.model.enums.PublicExposureRisk;

@Entity
@Table(name = "civilian_impact")
public class CivilianImpact {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer evacuated;
    private Integer injured;
    private Integer missing;
    @Enumerated(EnumType.STRING)
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

