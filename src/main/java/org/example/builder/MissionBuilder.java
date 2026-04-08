package org.example.builder;

import org.example.model.*;

import java.time.LocalDate;
import java.util.List;

public interface MissionBuilder {
    void setMissionId(String missionId);
    void setDate(LocalDate date);
    void setLocation(String location);
    void setOutcome(Outcome outcome);
    void setDamageCost(Long damageCost);
    void setCurse(Curse curse);
    void setSorcerers(List<Sorcerer> sorcerers);
    void setTechniques(List<Technique> techniques);
    void setEconomicAssessment(EconomicAssessment economicAssessment);
    void setCivilianImpact(CivilianImpact civilianImpact);
    void setEnemyActivity(EnemyActivity enemyActivity);
    void setEnvironmentConditions(EnvironmentConditions environmentConditions);
    void setOperationTimeline(List<TimelineEvent> operationTimeline);
    void setOperationTags(List<String> operationTags);
    void setSupportUnits(List<String> supportUnits);
    void setRecommendations(List<String> recommendations);
    void setNotes(String notes);
    void setArtifactsRecovered(List<String> artifactsRecovered);
    void setEvacuationZones(List<String> evacuationZones);
    void setStatusEffects(List<String> statusEffects);
    Mission build();
}
