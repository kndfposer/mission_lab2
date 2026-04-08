package org.example.builder;

import org.example.model.*;
import org.example.model.enums.MissionOutcome;

import java.time.LocalDate;

public interface MissionBuilder {
    MissionBuilder missionId(String value);
    MissionBuilder date(LocalDate value);
    MissionBuilder location(String value);
    MissionBuilder outcome(MissionOutcome value);
    MissionBuilder damageCost(Long value);
    MissionBuilder curse(Curse value);
    MissionBuilder addSorcerer(Sorcerer value);
    MissionBuilder addTechnique(Technique value);
    MissionBuilder economicAssessment(EconomicAssessment value);
    MissionBuilder civilianImpact(CivilianImpact value);
    MissionBuilder enemyActivity(EnemyActivity value);
    MissionBuilder environmentConditions(EnvironmentConditions value);
    MissionBuilder addTimelineEvent(TimelineEvent value);
    MissionBuilder addOperationTag(String value);
    MissionBuilder addSupportUnit(String value);
    MissionBuilder addRecommendation(String value);
    MissionBuilder notes(String value);
    MissionBuilder addArtifactRecovered(String value);
    MissionBuilder addEvacuationZone(String value);
    MissionBuilder addStatusEffect(String value);
    Mission build();
}
