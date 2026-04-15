package org.example.loader.parsers;

import org.example.builder.DefaultMissionBuilder;
import org.example.builder.MissionDirector;
import org.example.model.*;
import org.example.model.enums.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TxtMissionParser {

    public Mission parse(File file) throws IOException {
        MissionDirector director = new MissionDirector(new DefaultMissionBuilder());

        List<String> lines = Files.readAllLines(file.toPath());

        String section = "";

        Sorcerer currentSorcerer = null;
        Technique currentTechnique = null;
        TimelineEvent currentTimelineEvent = null;

        Curse currentCurse = null;
        EconomicAssessment currentEconomic = null;
        CivilianImpact currentCivilian = null;
        EnemyActivity currentEnemy = null;
        EnvironmentConditions currentEnvironment = null;

        for (String raw : lines) {
            String line = raw.trim();

            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            if (!line.startsWith("[") && line.contains(":") && !line.contains("=")) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim().toLowerCase();
                    String value = parts[1].trim();

                    if ("note".equals(key) || "notes".equals(key)) {
                        director.setNotes(value);
                        continue;
                    }
                }
            }

            if (line.startsWith("[") && line.endsWith("]")) {
                flushCurrentObjects(director, currentSorcerer, currentTechnique, currentTimelineEvent);

                currentSorcerer = null;
                currentTechnique = null;
                currentTimelineEvent = null;

                section = normalizeSection(line);

                switch (section) {
                    case "[SORCERER]" -> currentSorcerer = new Sorcerer();
                    case "[TECHNIQUE]" -> currentTechnique = new Technique();
                    case "[TIMELINE]" -> currentTimelineEvent = new TimelineEvent();
                    case "[CURSE]" -> {
                        if (currentCurse == null) currentCurse = new Curse();
                    }
                    case "[ECONOMIC]" -> {
                        if (currentEconomic == null) currentEconomic = new EconomicAssessment();
                    }
                    case "[CIVILIAN]" -> {
                        if (currentCivilian == null) currentCivilian = new CivilianImpact();
                    }
                    case "[ENEMY]" -> {
                        if (currentEnemy == null) currentEnemy = new EnemyActivity();
                    }
                    case "[ENVIRONMENT]" -> {
                        if (currentEnvironment == null) currentEnvironment = new EnvironmentConditions();
                    }
                }
                continue;
            }

            String[] parts = line.split("=", 2);
            if (parts.length != 2) {
                continue;
            }

            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (section) {
                case "[MISSION]" -> parseMission(director, key, value);

                case "[CURSE]" -> {
                    if (currentCurse == null) currentCurse = new Curse();
                    parseCurse(currentCurse, key, value);
                    director.setCurse(currentCurse);
                }

                case "[SORCERER]" -> {
                    if (currentSorcerer == null) currentSorcerer = new Sorcerer();
                    parseSorcerer(currentSorcerer, key, value);
                }

                case "[TECHNIQUE]" -> {
                    if (currentTechnique == null) currentTechnique = new Technique();


                    parseTechnique(currentTechnique, key, value);
                }

                case "[ECONOMIC]" -> {
                    if (currentEconomic == null) currentEconomic = new EconomicAssessment();
                    parseEconomic(currentEconomic, key, value);
                    director.setEconomicAssessment(currentEconomic);
                }

                case "[CIVILIAN]" -> {
                    if (currentCivilian == null) currentCivilian = new CivilianImpact();
                    parseCivilian(currentCivilian, key, value);
                    director.setCivilianImpact(currentCivilian);
                }

                case "[ENEMY]" -> {
                    if (currentEnemy == null) currentEnemy = new EnemyActivity();
                    parseEnemy(currentEnemy, key, value);
                    director.setEnemyActivity(currentEnemy);
                }

                case "[ENVIRONMENT]" -> {
                    if (currentEnvironment == null) currentEnvironment = new EnvironmentConditions();
                    parseEnvironment(currentEnvironment, key, value);
                    director.setEnvironmentConditions(currentEnvironment);
                }

                case "[TIMELINE]" -> {
                    if (currentTimelineEvent == null) currentTimelineEvent = new TimelineEvent();
                    parseTimeline(currentTimelineEvent, key, value);
                }

                case "[EXTRA]" -> parseExtra(director, key, value);
            }
        }

        flushCurrentObjects(director, currentSorcerer, currentTechnique, currentTimelineEvent);

        return director.build();
    }

    private void flushCurrentObjects(MissionDirector director,
                                     Sorcerer currentSorcerer,
                                     Technique currentTechnique,
                                     TimelineEvent currentTimelineEvent) {
        if (currentSorcerer != null) {
            director.addSorcerer(currentSorcerer);
        }
        if (currentTechnique != null) {
            director.addTechnique(currentTechnique);
        }
        if (currentTimelineEvent != null) {
            director.addTimelineEvent(currentTimelineEvent);
        }
    }

    private String normalizeSection(String rawSection) {
        String upper = rawSection.trim().toUpperCase();
        return switch (upper) {
            case "[MISSION]" -> "[MISSION]";
            case "[CURSE]" -> "[CURSE]";
            case "[SORCERER]" -> "[SORCERER]";
            case "[TECHNIQUE]" -> "[TECHNIQUE]";
            case "[ECONOMIC]", "[ECONOMICASSESSMENT]", "[ECONOMIC_ASSESSMENT]" -> "[ECONOMIC]";
            case "[CIVILIAN]", "[CIVILIANIMPACT]", "[CIVILIAN_IMPACT]" -> "[CIVILIAN]";
            case "[ENEMY]", "[ENEMYACTIVITY]", "[ENEMY_ACTIVITY]" -> "[ENEMY]";
            case "[ENVIRONMENT]", "[ENVIRONMENTCONDITIONS]", "[ENVIRONMENT_CONDITIONS]" -> "[ENVIRONMENT]";
            case "[TIMELINE]", "[OPERATIONTIMELINE]", "[OPERATION_TIMELINE]" -> "[TIMELINE]";
            case "[EXTRA]" -> "[EXTRA]";
            default -> upper;
        };
    }

    private void parseMission(MissionDirector director, String key, String value) {
        switch (key) {
            case "missionId" -> director.setBaseInfo(value, null, null, MissionOutcome.UNKNOWN, null);
            case "date" -> {
                Mission temp = director.build();
                rebuildBaseInfo(director, temp.getMissionId(), parseDate(value), temp.getLocation(), temp.getOutcome(), temp.getDamageCost());
            }
            case "location" -> {
                Mission temp = director.build();
                rebuildBaseInfo(director, temp.getMissionId(), temp.getDate(), value, temp.getOutcome(), temp.getDamageCost());
            }
            case "outcome" -> {
                Mission temp = director.build();
                rebuildBaseInfo(director, temp.getMissionId(), temp.getDate(), temp.getLocation(),
                        MissionOutcome.fromString(value), temp.getDamageCost());
            }
            case


                    "damageCost" -> {
                Mission temp = director.build();
                rebuildBaseInfo(director, temp.getMissionId(), temp.getDate(), temp.getLocation(),
                        temp.getOutcome(), parseLong(value));
            }
        }
    }

    private void rebuildBaseInfo(MissionDirector director,
                                 String missionId,
                                 LocalDate date,
                                 String location,
                                 MissionOutcome outcome,
                                 Long damageCost) {
        director.setBaseInfo(missionId, date, location, outcome, damageCost);
    }

    private void parseCurse(Curse curse, String key, String value) {
        switch (key) {
            case "name" -> curse.setName(value);
            case "threatLevel" -> curse.setThreatLevel(ThreatLevel.fromString(value));
        }
    }

    private void parseSorcerer(Sorcerer sorcerer, String key, String value) {
        switch (key) {
            case "name" -> sorcerer.setName(value);
            case "rank" -> sorcerer.setRank(SorcererRank.fromString(value));
        }
    }

    private void parseTechnique(Technique technique, String key, String value) {
        switch (key) {
            case "name" -> technique.setName(value);
            case "type" -> technique.setType(TechniqueType.fromString(value));
            case "owner" -> technique.setOwner(value);
            case "damage" -> technique.setDamage(parseLong(value));
        }
    }

    private void parseEconomic(EconomicAssessment economic, String key, String value) {
        switch (key) {
            case "totalDamageCost" -> economic.setTotalDamageCost(parseLong(value));
            case "infrastructureDamage" -> economic.setInfrastructureDamage(parseLong(value));
            case "commercialDamage" -> economic.setCommercialDamage(parseLong(value));
            case "transportDamage" -> economic.setTransportDamage(parseLong(value));
            case "recoveryEstimateDays" -> economic.setRecoveryEstimateDays(parseInteger(value));
            case "insuranceCovered" -> economic.setInsuranceCovered(Boolean.parseBoolean(value));
        }
    }

    private void parseCivilian(CivilianImpact civilian, String key, String value) {
        switch (key) {
            case "evacuated" -> civilian.setEvacuated(parseInteger(value));
            case "injured" -> civilian.setInjured(parseInteger(value));
            case "missing" -> civilian.setMissing(parseInteger(value));
            case "publicExposureRisk" -> civilian.setPublicExposureRisk(PublicExposureRisk.fromString(value));
        }
    }

    private void parseEnemy(EnemyActivity enemy, String key, String value) {
        switch (key) {
            case "behaviorType" -> enemy.setBehaviorType(value);
            case "mobility" -> enemy.setMobility(Mobility.fromString(value));
            case "escalationRisk" -> enemy.setEscalationRisk(EscalationRisk.fromString(value));
            case "targetPriority" -> enemy.getTargetPriority().addAll(splitList(value));
            case "attackPatterns" -> enemy.getAttackPatterns().addAll(splitList(value));
            case "countermeasuresUsed" -> enemy.getCountermeasuresUsed().addAll(splitList(value));
        }
    }

    private void parseEnvironment(EnvironmentConditions environment, String key, String value) {
        switch (key) {
            case "weather" -> environment.setWeather(value);
            case "timeOfDay" -> environment.setTimeOfDay(value);
            case "visibility" -> environment.setVisibility(Visibility.fromString(value));
            case "cursedEnergyDensity" -> environment.setCursedEnergyDensity(parseInteger(value));
        }
    }

    private void parseTimeline(TimelineEvent event, String key, String value) {
        switch (key) {
            case "timestamp" -> event.setTimestamp(parseDateTime(value));
            case "type" -> event.setType(value);
            case "description" -> event.setDescription(value);
        }
    }

    private void


    parseExtra(MissionDirector director, String key, String value) {
        switch (key) {
            case "tags", "operationTags" -> splitList(value).forEach(director::addOperationTag);
            case "support", "supportUnits" -> splitList(value).forEach(director::addSupportUnit);
            case "recommendations" -> splitList(value).forEach(director::addRecommendation);
            case "artifacts", "artifactsRecovered" -> splitList(value).forEach(director::addArtifactRecovered);
            case "zones", "evacuationZones" -> splitList(value).forEach(director::addEvacuationZone);
            case "effects", "statusEffects" -> splitList(value).forEach(director::addStatusEffect);
            case "notes", "note" -> director.setNotes(value);
        }
    }

    private LocalDate parseDate(String value) {
        return (value == null || value.isBlank()) ? null : LocalDate.parse(value.trim());
    }

    private LocalDateTime parseDateTime(String value) {
        return (value == null || value.isBlank()) ? null : LocalDateTime.parse(value.trim());
    }

    private Long parseLong(String value) {
        if (value == null || value.isBlank()) return null;
        return Long.parseLong(value.trim());
    }

    private Integer parseInteger(String value) {
        if (value == null || value.isBlank()) return null;
        return Integer.parseInt(value.trim());
    }

    private List<String> splitList(String value) {
        if (value == null || value.isBlank()) {
            return List.of();
        }
        return Arrays.stream(value.split("[,|]"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}


