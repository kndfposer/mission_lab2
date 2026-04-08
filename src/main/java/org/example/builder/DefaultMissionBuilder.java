package org.example.builder;

import org.example.model.*;
import org.example.model.enums.MissionOutcome;

import java.time.LocalDate;

public class DefaultMissionBuilder implements MissionBuilder {
    private final Mission mission = new Mission();
    public MissionBuilder missionId(String value) { mission.setMissionId(value); return this; }
    public MissionBuilder date(LocalDate value) { mission.setDate(value); return this; }
    public MissionBuilder location(String value) { mission.setLocation(value); return this; }
    public MissionBuilder outcome(MissionOutcome value) { mission.setOutcome(value); return this; }
    public MissionBuilder damageCost(Long value) { mission.setDamageCost(value); return this; }
    public MissionBuilder curse(Curse value) { mission.setCurse(value); return this; }
    public MissionBuilder addSorcerer(Sorcerer value) { if (value != null) mission.getSorcerers().add(value); return this; }
    public MissionBuilder addTechnique(Technique value) { if (value != null) mission.getTechniques().add(value); return this; }
    public MissionBuilder economicAssessment(EconomicAssessment value) { mission.setEconomicAssessment(value); return this; }
    public MissionBuilder civilianImpact(CivilianImpact value) { mission.setCivilianImpact(value); return this; }
    public MissionBuilder enemyActivity(EnemyActivity value) { mission.setEnemyActivity(value); return this; }
    public MissionBuilder environmentConditions(EnvironmentConditions value) { mission.setEnvironmentConditions(value); return this; }
    public MissionBuilder addTimelineEvent(TimelineEvent value) { if (value != null) mission.getOperationTimeline().add(value); return this; }
    public MissionBuilder addOperationTag(String value) { if (value != null && !value.isBlank()) mission.getOperationTags().add(value); return this; }
    public MissionBuilder addSupportUnit(String value) { if (value != null && !value.isBlank()) mission.getSupportUnits().add(value); return this; }
    public MissionBuilder addRecommendation(String value) { if (value != null && !value.isBlank()) mission.getRecommendations().add(value); return this; }
    public MissionBuilder notes(String value) { mission.setNotes(value); return this; }
    public MissionBuilder addArtifactRecovered(String value) { if (value != null && !value.isBlank()) mission.getArtifactsRecovered().add(value); return this; }
    public MissionBuilder addEvacuationZone(String value) { if (value != null && !value.isBlank()) mission.getEvacuationZones().add(value); return this; }
    public MissionBuilder addStatusEffect(String value) { if (value != null && !value.isBlank()) mission.getStatusEffects().add(value); return this; }
    public Mission build() { return mission; }
}
