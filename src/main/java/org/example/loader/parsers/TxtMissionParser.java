package org.example.loader.parsers;

import org.example.builder.DefaultMissionBuilder;
import org.example.builder.MissionBuilder;
import org.example.model.*;
import org.example.model.enums.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

public class TxtMissionParser {
    public Mission parse(File file) throws IOException {
        MissionBuilder b = new DefaultMissionBuilder();
        List<String> lines = Files.readAllLines(file.toPath());
        String section = "";
        Sorcerer currentSorcerer = null;
        Technique currentTechnique = null;
        EnvironmentConditions env = null;
        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("[") && line.endsWith("]")) {
                if (currentSorcerer != null) { b.addSorcerer(currentSorcerer); currentSorcerer = null; }
                if (currentTechnique != null) { b.addTechnique(currentTechnique); currentTechnique = null; }
                section = line;
                if ("[SORCERER]".equals(section)) currentSorcerer = new Sorcerer();
                if ("[TECHNIQUE]".equals(section)) currentTechnique = new Technique();
                if ("[ENVIRONMENT]".equals(section)) env = new EnvironmentConditions();
                continue;
            }
            String[] parts = line.split("=", 2);
            if (parts.length != 2) continue;
            String key = parts[0].trim(); String value = parts[1].trim();
            switch (section) {
                case "[MISSION]" -> { if ("missionId".equals(key)) b.missionId(value); else if ("date".equals(key)) b.date(LocalDate.parse(value)); else if ("location".equals(key)) b.location(value); else if ("outcome".equals(key)) b.outcome(MissionOutcome.fromString(value)); else if ("damageCost".equals(key)) b.damageCost(Long.parseLong(value)); }
                case "[CURSE]" -> {
                    Curse c = new Curse();
                    // naive: create/update from current built curse impossible, so collect simple two-line block
                    // handled via temporary state omitted for brevity by immediate set when key found
                    if ("name".equals(key)) { c.setName(value); b.curse(c); }
                    else if ("threatLevel".equals(key)) {
                        Mission m = b.build();
                        Curse existing = m.getCurse() != null ? m.getCurse() : new Curse();
                        existing.setThreatLevel(ThreatLevel.fromString(value));
                        if (existing.getName()==null && m.getCurse()!=null) existing.setName(m.getCurse().getName());
                        b.curse(existing);
                    }
                }
                case "[SORCERER]" -> { if ("name".equals(key)) currentSorcerer.setName(value); else if ("rank".equals(key)) currentSorcerer.setRank(SorcererRank.fromString(value)); }
                case "[TECHNIQUE]" -> { if ("name".equals(key)) currentTechnique.setName(value); else if ("type".equals(key)) currentTechnique.setType(TechniqueType.fromString(value)); else if ("owner".equals(key)) currentTechnique.setOwner(value); else if ("damage".equals(key)) currentTechnique.setDamage(Long.parseLong(value)); }
                case "[ENVIRONMENT]" -> { if ("weather".equals(key)) env.setWeather(value); else if ("timeOfDay".equals(key)) env.setTimeOfDay(value); else if ("visibility".equals(key)) env.setVisibility(Visibility.fromString(value)); else if ("cursedEnergyDensity".equals(key)) env.setCursedEnergyDensity(Integer.parseInt(value)); b.environmentConditions(env); }
            }
        }
        if (currentSorcerer != null) b.addSorcerer(currentSorcerer);
        if (currentTechnique != null) b.addTechnique(currentTechnique);
        return b.build();
    }
}
