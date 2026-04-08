package org.example.builder;

import org.example.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultMissionBuilder implements MissionBuilder {
    private final Mission mission = new Mission();

    @Override
    public void setMissionId(String missionId) {
        mission.setMissionId(missionId);
    }

    @Override
    public void setDate(LocalDate date) {
        mission.setDate(date);
    }

    @Override
    public void setLocation(String location) {
        mission.setLocation(location);
    }

    @Override
    public void setOutcome(Outcome outcome) {
        mission.setOutcome(outcome);
    }

    @Override
    public void setDamageCost(Long damageCost) {
        mission.setDamageCost(damageCost);
    }

    @Override
    public void setCurse(Curse curse) {
        mission.setCurse(curse);
    }

    @Override
    public void setSorcerers(List<Sorcerer> sorcerers) {
        mission.setSorcerers(sorcerers == null ? new ArrayList<>() : sorcerers);
    }

    @Override
    public void setTechniques(List<Technique> techniques) {
        mission.setTechniques(techniques == null ? new ArrayList<>() : techniques);
    }

    @Override
    public void setEconomicAssessment(EconomicAssessment economicAssessment) {
        mission.setEconomicAssessment(economicAssessment);
    }

    @Override
    public void setCivilianImpact(CivilianImpact civilianImpact) {
        mission.setCivilianImpact(civilianImpact);
    }

    @Override
    public void setEnemyActivity(EnemyActivity enemyActivity) {
        mission.setEnemyActivity(enemyActivity);
    }

    @Override
    public void setEnvironmentConditions(EnvironmentConditions environmentConditions) {
        mission.setEnvironmentConditions(environmentConditions);
    }

    @Override
    public void setOperationTimeline(List<TimelineEvent> operationTimeline) {
        mission.setOperationTimeline(operationTimeline == null ? new ArrayList<>() : operationTimeline);
    }

    @Override
    public void setOperationTags(List<String> operationTags) {
        mission.setOperationTags(operationTags == null ? new ArrayList<>() : operationTags);
    }

    @Override
    public void setSupportUnits(List<String> supportUnits) {
        mission.setSupportUnits(supportUnits == null ? new ArrayList<>() : supportUnits);
    }

    @Override
    public void setRecommendations(List<String> recommendations) {
        mission.setRecommendations(recommendations == null ? new ArrayList<>() : recommendations);
    }

    @Override
    public void setNotes(String notes) {
        mission.setNotes(notes);
    }

    @Override
    public void setArtifactsRecovered(List<String> artifactsRecovered) {
        mission.setArtifactsRecovered(artifactsRecovered == null ? new ArrayList<>() : artifactsRecovered);
    }

    @Override
    public void setEvacuationZones(List<String> evacuationZones) {
        mission.setEvacuationZones(evacuationZones == null ? new ArrayList<>() : evacuationZones);
    }

    @Override
    public void setStatusEffects(List<String> statusEffects) {
        mission.setStatusEffects(statusEffects == null ? new ArrayList<>() : statusEffects);
    }

    @Override
    public Mission build() {
        return mission;
    }
}
