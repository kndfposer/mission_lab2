package org.example.loader;

import org.example.builder.MissionBuilder;
import org.example.loader.parsers.raw.*;
import org.example.model.*;
import org.example.util.ParseUtils;

import java.util.ArrayList;
import java.util.List;

public class JsonLikeMissionAdapter implements MissionDataAdapter<MissionRaw> {
    @Override
    public void fill(MissionRaw source, MissionBuilder builder) {
        builder.setMissionId(ParseUtils.clean(source.getMissionId()));
        builder.setDate(ParseUtils.parseDate(source.getDate()));
        builder.setLocation(ParseUtils.clean(source.getLocation()));
        builder.setOutcome(Outcome.fromString(source.getOutcome()));
        builder.setDamageCost(source.getDamageCost());
        builder.setCurse(mapCurse(source.getCurse()));
        builder.setSorcerers(mapSorcerers(source.getSorcerers()));
        builder.setTechniques(mapTechniques(source.getTechniques()));
        builder.setEconomicAssessment(mapEconomic(source.getEconomicAssessment()));
        builder.setCivilianImpact(mapCivilian(source.getCivilianImpact()));
        builder.setEnemyActivity(mapEnemy(source.getEnemyActivity()));
        builder.setEnvironmentConditions(mapEnvironment(source.getEnvironmentConditions()));
        builder.setOperationTimeline(mapTimeline(source.getOperationTimeline()));
        builder.setOperationTags(ParseUtils.normalizeList(source.getOperationTags()));
        builder.setSupportUnits(ParseUtils.normalizeList(source.getSupportUnits()));
        builder.setRecommendations(ParseUtils.normalizeList(source.getRecommendations()));
        builder.setNotes(ParseUtils.clean(source.getNotes()));
        builder.setArtifactsRecovered(ParseUtils.normalizeList(source.getArtifactsRecovered()));
        builder.setEvacuationZones(ParseUtils.normalizeList(source.getEvacuationZones()));
        builder.setStatusEffects(ParseUtils.normalizeList(source.getStatusEffects()));
    }

    private Curse mapCurse(CurseRaw raw) {
        if (raw == null) return null;
        Curse curse = new Curse();
        curse.setName(ParseUtils.clean(raw.getName()));
        curse.setThreatLevel(ThreatLevel.fromString(raw.getThreatLevel()));
        return curse;
    }

    private List<Sorcerer> mapSorcerers(List<SorcererRaw> raws) {
        List<Sorcerer> result = new ArrayList<>();
        if (raws == null) return result;
        for (SorcererRaw raw : raws) {
            Sorcerer s = new Sorcerer();
            s.setName(ParseUtils.clean(raw.getName()));
            s.setRank(SorcererRank.fromString(raw.getRank()));
            result.add(s);
        }
        return result;
    }

    private List<Technique> mapTechniques(List<TechniqueRaw> raws) {
        List<Technique> result = new ArrayList<>();
        if (raws == null) return result;
        for (TechniqueRaw raw : raws) {
            Technique t = new Technique();
            t.setName(ParseUtils.clean(raw.getName()));
            t.setType(TechniqueType.fromString(raw.getType()));
            t.setOwner(ParseUtils.clean(raw.getOwner()));
            t.setDamage(raw.getDamage());
            result.add(t);
        }
        return result;
    }

    private EconomicAssessment mapEconomic(EconomicAssessmentRaw raw) {
        if (raw == null) return null;
        EconomicAssessment e = new EconomicAssessment();
        e.setTotalDamageCost(raw.getTotalDamageCost());
        e.setInfrastructureDamage(raw.getInfrastructureDamage());
        e.setCommercialDamage(raw.getCommercialDamage());
        e.setTransportDamage(raw.getTransportDamage());
        e.setRecoveryEstimateDays(raw.getRecoveryEstimateDays());
        e.setInsuranceCovered(raw.getInsuranceCovered());
        return e;
    }

    private CivilianImpact mapCivilian(CivilianImpactRaw raw) {
        if (raw == null) return null;
        CivilianImpact c = new CivilianImpact();
        c.setEvacuated(raw.getEvacuated());
        c.setInjured(raw.getInjured());
        c.setMissing(raw.getMissing());
        c.setPublicExposureRisk(PublicExposureRisk.fromString(raw.getPublicExposureRisk()));
        return c;
    }

    private EnemyActivity mapEnemy(EnemyActivityRaw raw) {
        if (raw == null) return null;
        EnemyActivity e = new EnemyActivity();
        e.setBehaviorType(ParseUtils.clean(raw.getBehaviorType()));
        e.setTargetPriority(ParseUtils.normalizeList(raw.getTargetPriority()));
        e.setAttackPatterns(ParseUtils.normalizeList(raw.getAttackPatterns()));
        e.setCountermeasuresUsed(ParseUtils.normalizeList(raw.getCountermeasuresUsed()));
        e.setMobility(MobilityLevel.fromString(raw.getMobility()));
        e.setEscalationRisk(EscalationRisk.fromString(raw.getEscalationRisk()));
        return e;
    }

    private EnvironmentConditions mapEnvironment(EnvironmentConditionsRaw raw) {
        if (raw == null) return null;
        EnvironmentConditions e = new EnvironmentConditions();
        e.setWeather(ParseUtils.clean(raw.getWeather()));
        e.setTimeOfDay(ParseUtils.clean(raw.getTimeOfDay()));
        e.setVisibility(VisibilityLevel.fromString(raw.getVisibility()));
        e.setCursedEnergyDensity(raw.getCursedEnergyDensity());
        return e;
    }

    private List<TimelineEvent> mapTimeline(List<TimelineEventRaw> raws) {
        List<TimelineEvent> result = new ArrayList<>();
        if (raws == null) return result;
        for (TimelineEventRaw raw : raws) {
            TimelineEvent event = new TimelineEvent();
            event.setTimestamp(ParseUtils.parseDateTime(raw.getTimestamp()));
            event.setType(ParseUtils.clean(raw.getType()));
            event.setDescription(ParseUtils.clean(raw.getDescription()));
            result.add(event);
        }
        return result;
    }
}
