package org.example.report;

import org.example.model.*;

public class SummaryMissionReportStrategy implements MissionReportStrategy {
    @Override
    public String generate(Mission mission) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== СВОДКА ПО МИССИИ === ");
                sb.append("ID: ").append(mission.getMissionId()).append(' ');
        sb.append("Дата: ").append(mission.getDate()).append(' ');
        sb.append("Локация: ").append(mission.getLocation()).append(' ');
        sb.append("Результат: ").append(mission.getOutcome()).append(' ');
        if (mission.getDamageCost() != null) sb.append("Ущерб: ").append(mission.getDamageCost()).append(' ');
        sb.append("Проклятие: ").append(mission.getCurse().getName()).append(" / ").append(mission.getCurse().getThreatLevel()).append(' ');
        if (!mission.getSorcerers().isEmpty()) {
            sb.append("Участники: ");
            for (Sorcerer s : mission.getSorcerers()) sb.append(" - ").append(s.getName()).append(" (").append(s.getRank()).append(") ");
        }
        if (!mission.getTechniques().isEmpty()) {
            sb.append("Техники: ");
            for (Technique t : mission.getTechniques()) sb.append(" - ").append(t.getName()).append(", ").append(t.getType()).append(", владелец: ").append(t.getOwner()).append(t.getDamage()!=null?", урон: "+t.getDamage():"").append(' ');
        }
        if (mission.getNotes() != null) sb.append("Примечание: ").append(mission.getNotes()).append(' ');
        return sb.toString();
    }
}
