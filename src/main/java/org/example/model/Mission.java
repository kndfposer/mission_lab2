package org.example.model;

import org.example.model.enums.MissionOutcome;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Mission {
    private String missionId;
    private LocalDate date;
    private String location;
    private MissionOutcome outcome = MissionOutcome.UNKNOWN;
    private Long damageCost;
    private Curse curse;
    private final List<Sorcerer> sorcerers = new ArrayList<>();
    private final List<Technique> techniques = new ArrayList<>();
    private EconomicAssessment economicAssessment;
    private CivilianImpact civilianImpact;
    private EnemyActivity enemyActivity;
    private EnvironmentConditions environmentConditions;
    private final List<TimelineEvent> operationTimeline = new ArrayList<>();
    private final List<String> operationTags = new ArrayList<>();
    private final List<String> supportUnits = new ArrayList<>();
    private final List<String> recommendations = new ArrayList<>();
    private String notes;
    private final List<String> artifactsRecovered = new ArrayList<>();
    private final List<String> evacuationZones = new ArrayList<>();
    private final List<String> statusEffects = new ArrayList<>();

    public String getMissionId() { return missionId; }
    public void setMissionId(String missionId) { this.missionId = missionId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public MissionOutcome getOutcome() { return outcome; }
    public void setOutcome(MissionOutcome outcome) { this.outcome = outcome; }
    public Long getDamageCost() { return damageCost; }
    public void setDamageCost(Long damageCost) { this.damageCost = damageCost; }
    public Curse getCurse() { return curse; }
    public void setCurse(Curse curse) { this.curse = curse; }
    public List<Sorcerer> getSorcerers() { return sorcerers; }
    public List<Technique> getTechniques() { return techniques; }
    public EconomicAssessment getEconomicAssessment() { return economicAssessment; }
    public void setEconomicAssessment(EconomicAssessment economicAssessment) { this.economicAssessment = economicAssessment; }
    public CivilianImpact getCivilianImpact() { return civilianImpact; }
    public void setCivilianImpact(CivilianImpact civilianImpact) { this.civilianImpact = civilianImpact; }
    public EnemyActivity getEnemyActivity() { return enemyActivity; }
    public void setEnemyActivity(EnemyActivity enemyActivity) { this.enemyActivity = enemyActivity; }
    public EnvironmentConditions getEnvironmentConditions() { return environmentConditions; }
    public void setEnvironmentConditions(EnvironmentConditions environmentConditions) { this.environmentConditions = environmentConditions; }
    public List<TimelineEvent> getOperationTimeline() { return operationTimeline; }
    public List<String> getOperationTags() { return operationTags; }
    public List<String> getSupportUnits() { return supportUnits; }
    public List<String> getRecommendations() { return recommendations; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public List<String> getArtifactsRecovered() { return artifactsRecovered; }
    public List<String> getEvacuationZones() { return evacuationZones; }
    public List<String> getStatusEffects() { return statusEffects; }
}
