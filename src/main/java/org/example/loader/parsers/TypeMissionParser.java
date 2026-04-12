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

public class TypeMissionParser {

    public Mission parse(File file) throws IOException {
        MissionDirector director = new MissionDirector(new DefaultMissionBuilder());

        Curse curse = new Curse();
        EnemyActivity enemy = new EnemyActivity();
        CivilianImpact civil = new CivilianImpact();

        String missionId = null;
        LocalDate date = null;
        String location = null;
        MissionOutcome outcome = MissionOutcome.UNKNOWN;
        Long damageCost = null;

        for (String raw : Files.readAllLines(file.toPath())) {
            String line = raw.trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\|");

            switch (p[0]) {
                case "MISSION_CREATED" -> {
                    missionId = p[1];
                    date = LocalDate.parse(p[2]);
                    location = p[3];
                    director.setBaseInfo(missionId, date, location, outcome, damageCost);
                }
                case "CURSE_DETECTED" -> {
                    curse.setName(p[1]);
                    curse.setThreatLevel(ThreatLevel.fromString(p[2]));
                    director.setCurse(curse);
                }
                case "SORCERER_ASSIGNED" -> {
                    Sorcerer s = new Sorcerer();
                    s.setName(p[1]);
                    s.setRank(SorcererRank.fromString(p[2]));
                    director.addSorcerer(s);
                }
                case "TECHNIQUE_USED" -> {
                    Technique t = new Technique();
                    t.setName(p[1]);
                    t.setType(TechniqueType.fromString(p[2]));
                    t.setOwner(p[3]);
                    t.setDamage(Long.parseLong(p[4]));
                    director.addTechnique(t);
                }
                case "TIMELINE_EVENT" -> {
                    TimelineEvent ev = new TimelineEvent();
                    ev.setTimestamp(LocalDateTime.parse(p[1]));
                    ev.setType(p[2]);
                    ev.setDescription(p[3]);
                    director.addTimelineEvent(ev);
                }
                case "ENEMY_ACTION" -> {
                    enemy.setBehaviorType(p[1]);
                    enemy.getAttackPatterns().add(p[2]);
                    director.setEnemyActivity(enemy);
                }
                case "CIVILIAN_IMPACT" -> {
                    for (int i = 1; i < p.length; i++) {
                        String[] kv = p[i].split("=", 2);
                        if (kv.length != 2) continue;

                        if ("evacuated".equals(kv[0])) civil.setEvacuated(Integer.parseInt(kv[1]));
                        if ("injured".equals(kv[0])) civil.setInjured(Integer.parseInt(kv[1]));
                        if ("missing".equals(kv[0])) civil.setMissing(Integer.parseInt(kv[1]));
                    }
                    director.setCivilianImpact(civil);
                }
                case "MISSION_RESULT" -> {
                    outcome = MissionOutcome.fromString(p[1]);
                    if (p.length > 2 && p[2].startsWith("damageCost=")) {
                        damageCost = Long.parseLong(p[2].substring("damageCost=".length()));
                    }
                    director.setBaseInfo(missionId, date, location, outcome, damageCost);
                }
            }
        }

        return director.build();
    }
}