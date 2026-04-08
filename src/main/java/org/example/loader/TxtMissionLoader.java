package org.example.loader;

import org.example.builder.DefaultMissionBuilder;
import org.example.builder.MissionBuilder;
import org.example.model.*;
import org.example.util.MissionValidator;
import org.example.util.ParseUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

public class TxtMissionLoader implements MissionLoader {
    @Override
    public String getFormatName() {
        return "TXT";
    }
    @Override
    public boolean supports(File file) {
        return org.example.util.MissionFormatSniffer.looksLikeSectionText(file);
    }

    @Override
    public Mission load(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        MissionBuilder builder = new DefaultMissionBuilder();

        String section = null;
        Curse curse = null;
        EconomicAssessment economic = null;
        CivilianImpact civilian = null;
        EnemyActivity enemy = null;
        EnvironmentConditions environment = null;
        List<Sorcerer> sorcerers = new ArrayList<>();
        List<Technique> techniques = new ArrayList<>();
        List<TimelineEvent> timeline = new ArrayList<>();
        List<String> operationTags = new ArrayList<>();
        List<String> supportUnits = new ArrayList<>();
        List<String> recommendations = new ArrayList<>();
        List<String> artifactsRecovered = new ArrayList<>();
        List<String> evacuationZones = new ArrayList<>();
        List<String> statusEffects = new ArrayList<>();

        Sorcerer currentSorcerer = null;
        Technique currentTechnique = null;
        TimelineEvent currentEvent = null;

        for (String rawLine : lines) {
            String line = rawLine.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            if (line.startsWith("[") && line.endsWith("]")) {
                if ("SORCERER".equals(section) && currentSorcerer != null) sorcerers.add(currentSorcerer);
                if ("TECHNIQUE".equals(section) && currentTechnique != null) techniques.add(currentTechnique);
                if ("TIMELINE_EVENT".equals(section) && currentEvent != null) timeline.add(currentEvent);

                section = line.substring(1, line.length() - 1).trim().toUpperCase();
                if ("SORCERER".equals(section)) currentSorcerer = new Sorcerer();
                if ("TECHNIQUE".equals(section)) currentTechnique = new Technique();
                if ("TIMELINE_EVENT".equals(section)) currentEvent = new TimelineEvent();
                if ("CURSE".equals(section) && curse == null) curse = new Curse();
                if ("ECONOMIC_ASSESSMENT".equals(section) && economic == null) economic = new EconomicAssessment();
                if ("CIVILIAN_IMPACT".equals(section) && civilian == null) civilian = new CivilianImpact();
                if ("ENEMY_ACTIVITY".equals(section) && enemy == null) enemy = new EnemyActivity();
                if ("ENVIRONMENT".equals(section) || "ENVIRONMENT_CONDITIONS".equals(section)) {
                    if (environment == null) environment = new EnvironmentConditions();
                }
                continue;
            }

            String[] parts = line.split("=", 2);
            if (parts.length < 2) continue;
            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (section == null ? "" : section) {
                case "MISSION" -> {
                    if ("missionId".equals(key)) builder.setMissionId(value);
                    else if ("date".equals(key)) builder.setDate(ParseUtils.parseDate(value));
                    else if ("location".equals(key)) builder.setLocation(value);
                    else if ("outcome".equals(key)) builder.setOutcome(Outcome.fromString(value));
                    else if ("damageCost".equals(key)) builder.setDamageCost(ParseUtils.parseLong(value));
                    else if ("notes".equals(key) || "note".equals(key)) builder.setNotes(value);
                }
                case "CURSE" -> {
                    if ("name".equals(key)) curse.setName(value);
                    else if ("threatLevel".equals(key)) curse.setThreatLevel(ThreatLevel.fromString(value));
                }
                case "SORCERER" -> {
                    if ("name".equals(key)) currentSorcerer.setName(value);
                    else if ("rank".equals(key)) currentSorcerer.setRank(SorcererRank.fromString(value));
                }
                case "TECHNIQUE" -> {
                    if ("name".equals(key)) currentTechnique.setName(value);
                    else if ("type".equals(key)) currentTechnique.setType(TechniqueType.fromString(value));
                    else if ("owner".equals(key)) currentTechnique.setOwner(value);
                    else if ("damage".equals(key)) currentTechnique.setDamage(ParseUtils.parseLong(value));
                }
                case "ECONOMIC_ASSESSMENT" -> applyEconomic(economic, key, value);
                case "CIVILIAN_IMPACT" -> applyCivilian(civilian, key, value);
                case "ENEMY_ACTIVITY" -> applyEnemy(enemy, key, value);
                case "ENVIRONMENT", "ENVIRONMENT_CONDITIONS" -> applyEnvironment(environment, key, value);
                case "TIMELINE_EVENT" -> {
                    if ("timestamp".equals(key)) currentEvent.setTimestamp(ParseUtils.parseDateTime(value));
                    else if ("type".equals(key)) currentEvent.setType(value);
                    else if ("description".equals(key)) currentEvent.setDescription(value);
                }
                case "OPERATION_TAGS" -> operationTags.add(value);
                case "SUPPORT_UNITS" -> supportUnits.add(value);
                case "RECOMMENDATIONS" -> recommendations.add(value);
                case "ARTIFACTS_RECOVERED" -> artifactsRecovered.add(value);
                case "EVACUATION_ZONES" -> evacuationZones.add(value);
                case "STATUS_EFFECTS" -> statusEffects.add(value);
            }
        }

        if ("SORCERER".equals(section) && currentSorcerer != null) sorcerers.add(currentSorcerer);
        if ("TECHNIQUE".equals(section) && currentTechnique != null) techniques.add(currentTechnique);
        if ("TIMELINE_EVENT".equals(section) && currentEvent != null) timeline.add(currentEvent);

        builder.setCurse(curse);
        builder.setSorcerers(sorcerers);
        builder.setTechniques(techniques);
        builder.setEconomicAssessment(economic);
        builder.setCivilianImpact(civilian);
        builder.setEnemyActivity(enemy);
        builder.setEnvironmentConditions(environment);
        builder.setOperationTimeline(timeline);
        builder.setOperationTags(operationTags);
        builder.setSupportUnits(supportUnits);
        builder.setRecommendations(recommendations);
        builder.setArtifactsRecovered(artifactsRecovered);
        builder.setEvacuationZones(evacuationZones);
        builder.setStatusEffects(statusEffects);

        Mission mission = builder.build();
        MissionValidator.validate(mission);
        return mission;
    }

    private void applyEconomic(EconomicAssessment economic, String key, String value) {
        if (economic == null) return;
        switch (key) {
            case "totalDamageCost" -> economic.setTotalDamageCost(ParseUtils.parseLong(value));
            case "infrastructureDamage" -> economic.setInfrastructureDamage(ParseUtils.parseLong(value));
            case "commercialDamage" -> economic.setCommercialDamage(ParseUtils.parseLong(value));
            case "transportDamage" -> economic.setTransportDamage(ParseUtils.parseLong(value));
            case "recoveryEstimateDays" -> economic.setRecoveryEstimateDays(ParseUtils.parseInteger(value));
            case "insuranceCovered" -> economic.setInsuranceCovered(ParseUtils.parseBoolean(value));
        }
    }

    private void applyCivilian(CivilianImpact civilian, String key, String value) {
        if (civilian == null) return;
        switch (key) {
            case "evacuated" -> civilian.setEvacuated(ParseUtils.parseInteger(value));
            case "injured" -> civilian.setInjured(ParseUtils.parseInteger(value));
            case "missing" -> civilian.setMissing(ParseUtils.parseInteger(value));
            case "publicExposureRisk" -> civilian.setPublicExposureRisk(PublicExposureRisk.fromString(value));
        }
    }

    private void applyEnemy(EnemyActivity enemy, String key, String value) {
        if (enemy == null) return;
        switch (key) {
            case "behaviorType" -> enemy.setBehaviorType(value);
            case "targetPriority" -> enemy.getTargetPriority().add(value);
            case "attackPattern", "attackPatterns" -> enemy.getAttackPatterns().add(value);
            case "countermeasure", "countermeasuresUsed" -> enemy.getCountermeasuresUsed().add(value);
            case "mobility" -> enemy.setMobility(MobilityLevel.fromString(value));
            case "escalationRisk" -> enemy.setEscalationRisk(EscalationRisk.fromString(value));
        }
    }

    private void applyEnvironment(EnvironmentConditions environment, String key, String value) {
        if (environment == null) return;
        switch (key) {
            case "weather" -> environment.setWeather(value);
            case "timeOfDay" -> environment.setTimeOfDay(value);
            case "visibility" -> environment.setVisibility(VisibilityLevel.fromString(value));
            case "cursedEnergyDensity" -> environment.setCursedEnergyDensity(ParseUtils.parseInteger(value));
        }
    }
}
