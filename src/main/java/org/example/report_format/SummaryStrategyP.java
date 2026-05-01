package org.example.report_format;

import org.example.model.*;

public class SummaryStrategyP implements Strategy_p {
    @Override
    public String generate(Mission mission) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== СВОДКА ПО МИССИИ === ");
                sb.append("ID: ").append(value(mission.getMissionId())).append(" ");
                        sb.append("Дата: ").append(value(mission.getDate())).append(" ");
                                sb.append("Локация: ").append(value(mission.getLocation())).append(" ");
                                        sb.append("Результат: ").append(value(mission.getOutcome())).append(" ");
        if (mission.getDamageCost() != null) sb.append("Ущерб: ").append(mission.getDamageCost()).append(" ");
        if (mission.getCurse() != null) {
            sb.append(" Проклятие");
            sb.append("  Название: ").append(value(mission.getCurse().getName())).append(" ");
                    sb.append("  Уровень угрозы: ").append(value(mission.getCurse().getThreatLevel())).append(" ");
        }
        if (!mission.getSorcerers().isEmpty()) {
            sb.append(" Участники:");
            for (Sorcerer s : mission.getSorcerers()) sb.append("  - ").append(value(s.getName())).append(" (").append(value(s.getRank())).append(") ");
        }
        if (!mission.getTechniques().isEmpty()) {
            sb.append(" Техники:");
            for (Technique t : mission.getTechniques()) {
                sb.append("  - ").append(value(t.getName())).append(" [").append(value(t.getType())).append("]")
                        .append(", владелец: ").append(value(t.getOwner()))
                        .append(", урон: ").append(value(t.getDamage())).append(" ");
            }
        }
        if (mission.getNotes() != null && !mission.getNotes().isBlank()) {
            sb.append(" Примечание: ").append(mission.getNotes()).append(" ");
        }
        return sb.toString();
    }

    private String value(Object value) { return value == null ? "-" : String.valueOf(value); }
}
