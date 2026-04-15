package org.example.porter.parsers;

import org.example.pattern_builder.DefMisBuilder;
import org.example.pattern_builder.MissionDirector;
import org.example.porter.parsers.raw.*;
import org.example.model.*;
import org.example.model.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class RawMissionMapper {

    private RawMissionMapper() {}

    public static Mission map(MissionRaw raw) {
        MissionDirector director = new MissionDirector(new DefMisBuilder());

        director.setBaseInfo(
                raw.missionId,
                parseDate(raw.date),
                raw.location,
                MissionOutcome.fromString(raw.outcome),
                raw.damageCost
        );

        if (raw.curse != null) {
            Curse curse = new Curse();
            curse.setName(raw.curse.name);
            curse.setThreatLevel(ThreatLevel.fromString(raw.curse.threatLevel));
            director.setCurse(curse);
        }

        if (raw.sorcerers != null) {
            for (SorcererRaw sr : raw.sorcerers) {
                Sorcerer s = new Sorcerer();
                s.setName(sr.name);
                s.setRank(SorcererRank.fromString(sr.rank));
                director.addSorcerer(s);
            }
        }

        if (raw.techniques != null) {
            for (TechniqueRaw tr : raw.techniques) {
                Technique t = new Technique();
                t.setName(tr.name);
                t.setType(TechniqueType.fromString(tr.type));
                t.setOwner(tr.owner);
                t.setDamage(tr.damage);
                director.addTechnique(t);
            }
        }

        if (raw.economicAssessment != null) {
            EconomicAssessment e = new EconomicAssessment();
            e.setTotalDamageCost(raw.economicAssessment.totalDamageCost);
            e.setInfrastructureDamage(raw.economicAssessment.infrastructureDamage);
            e.setCommercialDamage(raw.economicAssessment.commercialDamage);
            e.setTransportDamage(raw.economicAssessment.transportDamage);
            e.setRecoveryEstimateDays(raw.economicAssessment.recoveryEstimateDays);
            e.setInsuranceCovered(raw.economicAssessment.insuranceCovered);
            director.setEconomicAssessment(e);
        }

        if (raw.civilianImpact != null) {
            CivilianImpact c = new CivilianImpact();
            c.setEvacuated(raw.civilianImpact.evacuated);
            c.setInjured(raw.civilianImpact.injured);
            c.setMissing(raw.civilianImpact.missing);
            c.setPublicExposureRisk(PublicExposureRisk.fromString(raw.civilianImpact.publicExposureRisk));
            director.setCivilianImpact(c);
        }

        if (raw.enemyActivity != null) {
            EnemyActivity ea = new EnemyActivity();
            ea.setBehaviorType(raw.enemyActivity.behaviorType);
            if (raw.enemyActivity.targetPriority != null) {
                ea.getTargetPriority().addAll(raw.enemyActivity.targetPriority);
            }
            if (raw.enemyActivity.attackPatterns != null) {
                ea.getAttackPatterns().addAll(raw.enemyActivity.attackPatterns);
            }
            ea.setMobility(Mobility.fromString(raw.enemyActivity.mobility));
            ea.setEscalationRisk(EscalationRisk.fromString(raw.enemyActivity.escalationRisk));
            if (raw.enemyActivity.countermeasuresUsed != null) {
                ea.getCountermeasuresUsed().addAll(raw.enemyActivity.countermeasuresUsed);
            }
            director.setEnemyActivity(ea);
        }

        if (raw.environmentConditions != null) {
            EnvironmentConditions env = new EnvironmentConditions();
            env.setWeather(raw.environmentConditions.weather);
            env.setTimeOfDay(raw.environmentConditions.timeOfDay);
            env.setVisibility(Visibility.fromString(raw.environmentConditions.visibility));
            env.setCursedEnergyDensity(raw.environmentConditions.cursedEnergyDensity);
            director.setEnvironmentConditions(env);
        }

        if (raw.operationTimeline != null) {
            for (TimelineEventRaw tvr : raw.operationTimeline) {
                TimelineEvent te = new TimelineEvent();
                te.setTimestamp(parseDateTime(tvr.timestamp));
                te.setType(tvr.type);
                te.setDescription(tvr.description);
                director.addTimelineEvent(te);
            }
        }

        if (raw.operationTags != null) {
            for (String tag : raw.operationTags) {
                director.addOperationTag(tag);
            }
        }

        if (raw.supportUnits != null) {
            for (String unit : raw.supportUnits) {
                director.addSupportUnit(unit);
            }
        }

        if (raw.recommendations != null) {
            for (String recommendation : raw.recommendations) {
                director.addRecommendation(recommendation);
            }
        }

        if (raw.notes != null) {
            director.setNotes(raw.notes);
        }

        if (raw.artifactsRecovered != null) {
            for (String artifact : raw.artifactsRecovered) {
                director.addArtifactRecovered(artifact);
            }
        }

        if (raw.evacuationZones != null) {
            for (String zone : raw.evacuationZones) {
                director.addEvacuationZone(zone);
            }
        }

        if (raw.statusEffects != null) {
            for (String effect : raw.statusEffects) {
                director.addStatusEffect(effect);
            }
        }

        return director.build();
    }

    public static LocalDate parseDate(String v) {
        return v == null || v.isBlank() ? null : LocalDate.parse(v.trim());
    }

    public static LocalDateTime parseDateTime(String v) {
        return v == null || v.isBlank() ? null : LocalDateTime.parse(v.trim());
    }
}