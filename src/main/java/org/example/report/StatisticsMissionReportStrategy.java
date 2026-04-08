package org.example.report;

import org.example.model.Mission;
import org.example.model.Technique;

public class StatisticsMissionReportStrategy implements MissionReportStrategy {
    @Override
    public String createReport(Mission mission) {
        long totalTechniqueDamage = mission.getTechniques().stream()
                .map(Technique::getDamage)
                .filter(java.util.Objects::nonNull)
                .mapToLong(Long::longValue)
                .sum();

        return "СТАТИСТИЧЕСКИЙ ОТЧЁТ\n" +
                "Миссия: " + mission.getMissionId() + "\n" +
                "Количество участников: " + mission.getSorcerers().size() + "\n" +
                "Количество техник: " + mission.getTechniques().size() + "\n" +
                "Суммарный урон техник: " + totalTechniqueDamage + "\n" +
                "Событий в хронологии: " + mission.getOperationTimeline().size() + "\n" +
                "Тегов: " + mission.getOperationTags().size() + "\n" +
                "Подразделений поддержки: " + mission.getSupportUnits().size() + "\n";
    }
}
