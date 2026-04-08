package org.example.report;

import org.example.model.*;

import java.util.StringJoiner;

public class SummaryMissionReportStrategy implements MissionReportStrategy {
    @Override
    public String createReport(Mission mission) {
        StringBuilder sb = new StringBuilder();
        sb.append("КРАТКАЯ СВОДКА ПО МИССИИ\n");
        sb.append("missionId: ").append(mission.getMissionId()).append('\n');
        sb.append("date: ").append(mission.getDate()).append('\n');
        sb.append("location: ").append(mission.getLocation()).append('\n');
        sb.append("outcome: ").append(mission.getOutcome()).append('\n');
        if (mission.getDamageCost() != null) sb.append("damageCost: ").append(mission.getDamageCost()).append('\n');

        Curse curse = mission.getCurse();
        sb.append("\nПроклятие: ").append(curse.getName()).append(" [").append(curse.getThreatLevel()).append("]\n");

        if (!mission.getSorcerers().isEmpty()) {
            sb.append("\nУчастники:\n");
            for (Sorcerer sorcerer : mission.getSorcerers()) {
                sb.append("- ").append(sorcerer.getName()).append(" (").append(sorcerer.getRank()).append(")\n");
            }
        }

        if (!mission.getTechniques().isEmpty()) {
            sb.append("\nТехники:\n");
            for (Technique technique : mission.getTechniques()) {
                sb.append("- ").append(technique.getName())
                        .append(" [").append(technique.getType()).append("]")
                        .append(", owner=").append(technique.getOwner());
                if (technique.getDamage() != null) sb.append(", damage=").append(technique.getDamage());
                sb.append('\n');
            }
        }

        if (mission.getNotes() != null) {
            sb.append("\nnotes: ").append(mission.getNotes()).append('\n');
        }
        return sb.toString();
    }
}
