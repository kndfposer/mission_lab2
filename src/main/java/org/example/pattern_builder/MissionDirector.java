package org.example.pattern_builder;

import org.example.model.*;
import org.example.model.enums.MissionOutcome;

import java.time.LocalDate;

public class MissionDirector {
    private final MissionBuilder builder;

    public MissionDirector(MissionBuilder builder) {
        this.builder = builder;
    }

    public void setBaseInfo(String missionId, LocalDate date, String location,
                            MissionOutcome outcome, Long damageCost) {
        builder.missionId(missionId)
                .date(date)
                .location(location)
                .outcome(outcome)
                .damageCost(damageCost);
    }

    public void setCurse(Curse curse) {
        builder.curse(curse);
    }

    public void addSorcerer(Sorcerer sorcerer) {
        builder.addSorcerer(sorcerer);
    }

    public void addTechnique(Technique technique) {
        builder.addTechnique(technique);
    }

    public void setEconomicAssessment(EconomicAssessment value) {
        builder.economicAssessment(value);
    }

    public void setCivilianImpact(CivilianImpact value) {
        builder.civilianImpact(value);
    }

    public void setEnemyActivity(EnemyActivity value) {
        builder.enemyActivity(value);
    }

    public void setEnvironmentConditions(EnvironmentConditions value) {
        builder.environmentConditions(value);
    }

    public void addTimelineEvent(TimelineEvent value) {
        builder.addTimelineEvent(value);
    }

    public void addOperationTag(String value) {
        builder.addOperationTag(value);
    }

    public void addSupportUnit(String value) {
        builder.addSupportUnit(value);
    }

    public void addRecommendation(String value) {
        builder.addRecommendation(value);
    }

    public void setNotes(String value) {
        builder.notes(value);
    }

    public void addArtifactRecovered(String value) {
        builder.addArtifactRecovered(value);
    }

    public void addEvacuationZone(String value) {
        builder.addEvacuationZone(value);
    }

    public void addStatusEffect(String value) {
        builder.addStatusEffect(value);
    }

    public Mission build() {
        return builder.build();
    }
}