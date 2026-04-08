package org.example.loader.parsers.raw;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "mission")
public class MissionRaw {
    private String missionId;
    private String date;
    private String location;
    private String outcome;
    private Long damageCost;
    private CurseRaw curse;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "sorcerer")
    @JacksonXmlElementWrapper(localName = "sorcerers")
    private List<SorcererRaw> sorcerers = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "technique")
    @JacksonXmlElementWrapper(localName = "techniques")
    private List<TechniqueRaw> techniques = new ArrayList<>();

    private EconomicAssessmentRaw economicAssessment;
    private CivilianImpactRaw civilianImpact;
    private EnemyActivityRaw enemyActivity;
    private EnvironmentConditionsRaw environmentConditions;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "event")
    @JacksonXmlElementWrapper(localName = "operationTimeline")
    private List<TimelineEventRaw> operationTimeline = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "operationTags")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> operationTags = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "supportUnits")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> supportUnits = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "recommendations")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> recommendations = new ArrayList<>();

    private String notes;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "artifactsRecovered")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> artifactsRecovered = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "evacuationZones")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> evacuationZones = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "statusEffects")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> statusEffects = new ArrayList<>();

    public String getMissionId() { return missionId; }
    public void setMissionId(String missionId) { this.missionId = missionId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }
    public Long getDamageCost() { return damageCost; }
    public void setDamageCost(Long damageCost) { this.damageCost = damageCost; }
    public CurseRaw getCurse() { return curse; }
    public void setCurse(CurseRaw curse) { this.curse = curse; }
    public List<SorcererRaw> getSorcerers() { return sorcerers; }
    public void setSorcerers(List<SorcererRaw> sorcerers) { this.sorcerers = sorcerers; }
    public List<TechniqueRaw> getTechniques() { return techniques; }
    public void setTechniques(List<TechniqueRaw> techniques) { this.techniques = techniques; }
    public EconomicAssessmentRaw getEconomicAssessment() { return economicAssessment; }
    public void setEconomicAssessment(EconomicAssessmentRaw economicAssessment) { this.economicAssessment = economicAssessment; }
    public CivilianImpactRaw getCivilianImpact() { return civilianImpact; }
    public void setCivilianImpact(CivilianImpactRaw civilianImpact) { this.civilianImpact = civilianImpact; }
    public EnemyActivityRaw getEnemyActivity() { return enemyActivity; }
    public void setEnemyActivity(EnemyActivityRaw enemyActivity) { this.enemyActivity = enemyActivity; }
    public EnvironmentConditionsRaw getEnvironmentConditions() { return environmentConditions; }
    public void setEnvironmentConditions(EnvironmentConditionsRaw environmentConditions) { this.environmentConditions = environmentConditions; }
    public List<TimelineEventRaw> getOperationTimeline() { return operationTimeline; }
    public void setOperationTimeline(List<TimelineEventRaw> operationTimeline) { this.operationTimeline = operationTimeline; }
    public List<String> getOperationTags() { return operationTags; }
    public void setOperationTags(List<String> operationTags) { this.operationTags = operationTags; }
    public List<String> getSupportUnits() { return supportUnits; }
    public void setSupportUnits(List<String> supportUnits) { this.supportUnits = supportUnits; }
    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public List<String> getArtifactsRecovered() { return artifactsRecovered; }
    public void setArtifactsRecovered(List<String> artifactsRecovered) { this.artifactsRecovered = artifactsRecovered; }
    public List<String> getEvacuationZones() { return evacuationZones; }
    public void setEvacuationZones(List<String> evacuationZones) { this.evacuationZones = evacuationZones; }
    public List<String> getStatusEffects() { return statusEffects; }
    public void setStatusEffects(List<String> statusEffects) { this.statusEffects = statusEffects; }
}
