package org.example.loader;

import org.example.builder.DefaultMissionBuilder;
import org.example.builder.MissionBuilder;
import org.example.model.*;
import org.example.util.MissionValidator;
import org.example.util.ParseUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TypeMissionLoader implements MissionLoader {
    @Override
    public String getFormatName() {
        return "TYPE_EVENT";
    }
    @Override
    public boolean supports(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".type") || name.endsWith(".log");
    }

    @Override
    public Mission load(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        MissionBuilder builder = new DefaultMissionBuilder();
        Curse curse = new Curse();
        List<Sorcerer> sorcerers = new ArrayList<>();
        List<Technique> techniques = new ArrayList<>();
        List<TimelineEvent> timeline = new ArrayList<>();
        CivilianImpact civilianImpact = null;
        EnemyActivity enemyActivity = null;

        Long damageCost = null;
        Outcome outcome = null;

        for (String rawLine : lines) {
            String line = rawLine.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\|", -1);
            String type = parts[0].trim();

            switch (type) {
                case "MISSION_CREATED" -> {
                    builder.setMissionId(parts[1]);
                    builder.setDate(ParseUtils.parseDate(parts[2]));
                    builder.setLocation(parts[3]);
                }
                case "CURSE_DETECTED" -> {
                    curse.setName(parts[1]);
                    curse.setThreatLevel(ThreatLevel.fromString(parts[2]));
                }
                case "SORCERER_ASSIGNED" -> {
                    Sorcerer s = new Sorcerer();
                    s.setName(parts[1]);
                    s.setRank(SorcererRank.fromString(parts[2]));
                    sorcerers.add(s);
                }
                case "TECHNIQUE_USED" -> {
                    Technique technique = new Technique();
                    technique.setName(parts[1]);
                    technique.setType(TechniqueType.fromString(parts[2]));
                    technique.setOwner(parts[3]);
                    technique.setDamage(ParseUtils.parseLong(parts[4]));
                    techniques.add(technique);
                }
                case "TIMELINE_EVENT" -> {
                    TimelineEvent event = new TimelineEvent();
                    event.setTimestamp(ParseUtils.parseDateTime(parts[1]));
                    event.setType(parts[2]);
                    event.setDescription(parts[3]);
                    timeline.add(event);
                }
                case "ENEMY_ACTION" -> {
                    if (enemyActivity == null) enemyActivity = new EnemyActivity();
                    enemyActivity.setBehaviorType(parts[1]);
                    if (parts.length > 2) enemyActivity.getAttackPatterns().add(parts[2]);
                }
                case "CIVILIAN_IMPACT" -> {
                    if (civilianImpact == null) civilianImpact = new CivilianImpact();
                    for (int i = 1; i < parts.length; i++) {
                        String[] kv = parts[i].split("=", 2);
                        if (kv.length != 2) continue;
                        switch (kv[0]) {
                            case "evacuated" -> civilianImpact.setEvacuated(ParseUtils.parseInteger(kv[1]));
                            case "injured" -> civilianImpact.setInjured(ParseUtils.parseInteger(kv[1]));
                            case "missing" -> civilianImpact.setMissing(ParseUtils.parseInteger(kv[1]));
                        }
                    }
                }
                case "MISSION_RESULT" -> {
                    outcome = Outcome.fromString(parts[1]);
                    for (int i = 2; i < parts.length; i++) {
                        String[] kv = parts[i].split("=", 2);
                        if (kv.length == 2 && "damageCost".equals(kv[0])) {
                            damageCost = ParseUtils.parseLong(kv[1]);
                        }
                    }
                }
            }
        }

        builder.setCurse(curse);
        builder.setSorcerers(sorcerers);
        builder.setTechniques(techniques);
        builder.setOperationTimeline(timeline);
        builder.setCivilianImpact(civilianImpact);
        builder.setEnemyActivity(enemyActivity);
        builder.setOutcome(outcome);
        builder.setDamageCost(damageCost);

        Mission mission = builder.build();
        MissionValidator.validate(mission);
        return mission;
    }
}
