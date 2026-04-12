package org.example.loader.parsers;

import org.example.builder.DefaultMissionBuilder;
import org.example.builder.MissionDirector;
import org.example.model.*;
import org.example.model.enums.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

public class TxtMissionParser {

    public Mission parse(File file) throws IOException {
        MissionDirector director = new MissionDirector(new DefaultMissionBuilder());

        String section = "";
        Sorcerer currentSorcerer = null;
        Technique currentTechnique = null;
        EnvironmentConditions env = null;
        Curse currentCurse = null;

        List<String> lines = Files.readAllLines(file.toPath());

        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("[") && line.endsWith("]")) {
                if (currentSorcerer != null) {
                    director.addSorcerer(currentSorcerer);
                    currentSorcerer = null;
                }
                if (currentTechnique != null) {
                    director.addTechnique(currentTechnique);
                    currentTechnique = null;
                }

                section = line;

                if ("[SORCERER]".equals(section)) currentSorcerer = new Sorcerer();
                if ("[TECHNIQUE]".equals(section)) currentTechnique = new Technique();
                if ("[ENVIRONMENT]".equals(section)) env = new EnvironmentConditions();
                if ("[CURSE]".equals(section) && currentCurse == null) currentCurse = new Curse();

                continue;
            }

            String[] parts = line.split("=", 2);
            if (parts.length != 2) continue;

            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (section) {
                case "[MISSION]" -> {
                    if ("missionId".equals(key)) {
                        // base info собирается по частям, поэтому используем текущие значения через builder нельзя;
                        // просто задаём поля по мере чтения
                        director.setBaseInfo(value, null, null, MissionOutcome.UNKNOWN, null);
                    } else if ("date".equals(key)) {
                        Mission temp = director.build();
                        director = recreateDirectorWithBase(
                                temp.getMissionId(),
                                LocalDate.parse(value),
                                temp.getLocation(),
                                temp.getOutcome(),
                                temp.getDamageCost(),
                                temp
                        );
                    } else if ("location".equals(key)) {
                        Mission temp = director.build();
                        director = recreateDirectorWithBase(
                                temp.getMissionId(),
                                temp.getDate(),
                                value,
                                temp.getOutcome(),
                                temp.getDamageCost(),
                                temp
                        );
                    } else if ("outcome".equals(key)) {
                        Mission temp = director.build();
                        director = recreateDirectorWithBase(
                                temp.getMissionId(),
                                temp.getDate(),
                                temp.getLocation(),
                                MissionOutcome.fromString(value),
                                temp.getDamageCost(),
                                temp
                        );
                    } else if ("damageCost".equals(key)) {
                        Mission temp = director.build();
                        director = recreateDirectorWithBase(
                                temp.getMissionId(),
                                temp.getDate(),


                                temp.getLocation(),
                                temp.getOutcome(),
                                Long.parseLong(value),
                                temp
                        );
                    }
                }
                case "[CURSE]" -> {
                    if ("name".equals(key)) {
                        currentCurse.setName(value);
                    } else if ("threatLevel".equals(key)) {
                        currentCurse.setThreatLevel(ThreatLevel.fromString(value));
                    }
                    director.setCurse(currentCurse);
                }
                case "[SORCERER]" -> {
                    if ("name".equals(key)) currentSorcerer.setName(value);
                    else if ("rank".equals(key)) currentSorcerer.setRank(SorcererRank.fromString(value));
                }
                case "[TECHNIQUE]" -> {
                    if ("name".equals(key)) currentTechnique.setName(value);
                    else if ("type".equals(key)) currentTechnique.setType(TechniqueType.fromString(value));
                    else if ("owner".equals(key)) currentTechnique.setOwner(value);
                    else if ("damage".equals(key)) currentTechnique.setDamage(Long.parseLong(value));
                }
                case "[ENVIRONMENT]" -> {
                    if ("weather".equals(key)) env.setWeather(value);
                    else if ("timeOfDay".equals(key)) env.setTimeOfDay(value);
                    else if ("visibility".equals(key)) env.setVisibility(Visibility.fromString(value));
                    else if ("cursedEnergyDensity".equals(key)) env.setCursedEnergyDensity(Integer.parseInt(value));
                    director.setEnvironmentConditions(env);
                }
            }
        }

        if (currentSorcerer != null) director.addSorcerer(currentSorcerer);
        if (currentTechnique != null) director.addTechnique(currentTechnique);

        return director.build();
    }

    private MissionDirector recreateDirectorWithBase(
            String missionId,
            LocalDate date,
            String location,
            MissionOutcome outcome,
            Long damageCost,
            Mission oldMission
    ) {
        MissionDirector director = new MissionDirector(new DefaultMissionBuilder());
        director.setBaseInfo(missionId, date, location, outcome, damageCost);

        if (oldMission.getCurse() != null) director.setCurse(oldMission.getCurse());
        oldMission.getSorcerers().forEach(director::addSorcerer);
        oldMission.getTechniques().forEach(director::addTechnique);
        if (oldMission.getEconomicAssessment() != null) director.setEconomicAssessment(oldMission.getEconomicAssessment());
        if (oldMission.getCivilianImpact() != null) director.setCivilianImpact(oldMission.getCivilianImpact());
        if (oldMission.getEnemyActivity() != null) director.setEnemyActivity(oldMission.getEnemyActivity());
        if (oldMission.getEnvironmentConditions() != null) director.setEnvironmentConditions(oldMission.getEnvironmentConditions());
        oldMission.getOperationTimeline().forEach(director::addTimelineEvent);
        oldMission.getOperationTags().forEach(director::addOperationTag);
        oldMission.getSupportUnits().forEach(director::addSupportUnit);
        oldMission.getRecommendations().forEach(director::addRecommendation);
        director.setNotes(oldMission.getNotes());
        oldMission.getArtifactsRecovered().forEach(director::addArtifactRecovered);
        oldMission.getEvacuationZones().forEach(director::addEvacuationZone);
        oldMission.getStatusEffects().forEach(director::addStatusEffect);

        return director;
    }
}