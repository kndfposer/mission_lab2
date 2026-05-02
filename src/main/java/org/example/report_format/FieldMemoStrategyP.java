package org.example.report_format;

import org.example.model.Mission;

public class FieldMemoStrategyP implements Strategy_p {
    @Override
    public String generate(Mission mission) {
        String curse = mission.getCurse() == null ? "-" : mission.getCurse().getName();
        return """
               === ПОЛЕВАЯ ПАМЯТКА ===
               Код миссии: %s
               Локация: %s
               Результат: %s
               Проклятие: %s
               Участников: %d
               Техник: %d
               """.formatted(
                mission.getMissionId(),
                mission.getLocation(),
                mission.getOutcome(),
                curse,
                mission.getSorcerers().size(),
                mission.getTechniques().size());
    }
}
