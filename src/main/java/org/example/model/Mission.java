package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Mission {
    private String missionId;
    private LocalDate date;
    private String location;
    private Outcome outcome;
    private Long damageCost;
    private Curse curse;
    private List<Sorcerer> sorcerers = new ArrayList<>();
    private List<Technique> techniques = new ArrayList<>();
    private EconomicAssessment economicAssessment;
    private CivilianImpact civilianImpact;
    private EnemyActivity enemyActivity;
    private EnvironmentConditions environmentConditions;
    private List<TimelineEvent> operationTimeline = new ArrayList<>();
    private List<String> operationTags = new ArrayList<>();
    private List<String> supportUnits = new ArrayList<>();
    private List<String> recommendations = new ArrayList<>();
    private String notes;
    private List<String> artifactsRecovered = new ArrayList<>();
    private List<String> evacuationZones = new ArrayList<>();
    private List<String> statusEffects = new ArrayList<>();

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Long getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(Long damageCost) {
        this.damageCost = damageCost;
    }

    public Curse getCurse() {
        return curse;
    }

    public void setCurse(Curse curse) {
        this.curse = curse;
    }

    public List<Sorcerer> getSorcerers() {
        return sorcerers;
    }

    public void setSorcerers(List<Sorcerer> sorcerers) {
        this.sorcerers = sorcerers;
    }

    public List<Technique> getTechniques() {
        return techniques;
    }

    public void setTechniques(List<Technique> techniques) {
        this.techniques = techniques;
    }

    public EconomicAssessment getEconomicAssessment() {
        return economicAssessment;
    }

    public void setEconomicAssessment(EconomicAssessment economicAssessment) {
        this.economicAssessment = economicAssessment;
    }

    public CivilianImpact getCivilianImpact() {
        return civilianImpact;
    }

    public void setCivilianImpact(CivilianImpact civilianImpact) {
        this.civilianImpact = civilianImpact;
    }

    public EnemyActivity getEnemyActivity() {
        return enemyActivity;
    }

    public void setEnemyActivity(EnemyActivity enemyActivity) {
        this.enemyActivity = enemyActivity;
    }

    public EnvironmentConditions getEnvironmentConditions() {
        return environmentConditions;
    }

    public void setEnvironmentConditions(EnvironmentConditions environmentConditions) {
        this.environmentConditions = environmentConditions;
    }

    public List<TimelineEvent> getOperationTimeline() {
        return operationTimeline;
    }

    public void setOperationTimeline(List<TimelineEvent> operationTimeline) {
        this.operationTimeline = operationTimeline;
    }

    public List<String> getOperationTags() {
        return operationTags;
    }

    public void setOperationTags(List<String> operationTags) {
        this.operationTags = operationTags;
    }

    public List<String> getSupportUnits() {
        return supportUnits;
    }

    public void setSupportUnits(List<String> supportUnits) {
        this.supportUnits = supportUnits;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getArtifactsRecovered() {
        return artifactsRecovered;
    }

    public void setArtifactsRecovered(List<String> artifactsRecovered) {
        this.artifactsRecovered = artifactsRecovered;
    }

    public List<String> getEvacuationZones() {
        return evacuationZones;
    }

    public void setEvacuationZones(List<String> evacuationZones) {
        this.evacuationZones = evacuationZones;
    }

    public List<String> getStatusEffects() {
        return statusEffects;
    }

    public void setStatusEffects(List<String> statusEffects) {
        this.statusEffects = statusEffects;
    }
}
