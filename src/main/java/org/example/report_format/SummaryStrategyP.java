package org.example.report_format;

import org.example.model.CivilianImpact;
import org.example.model.EconomicAssessment;
import org.example.model.EnemyActivity;
import org.example.model.EnvironmentConditions;
import org.example.model.Mission;
import org.example.model.Sorcerer;
import org.example.model.Technique;
import org.example.model.TimelineEvent;

public class SummaryStrategyP implements Strategy_p {

    @Override
    public String generate(Mission mission) {
        StringBuilder sb = new StringBuilder();

        sb.append("=== СВОДКА ПО МИССИИ ===\n");
        sb.append("ID: ").append(value(mission.getMissionId())).append("\n");
        sb.append("Дата: ").append(value(mission.getDate())).append("\n");
        sb.append("Локация: ").append(value(mission.getLocation())).append("\n");
        sb.append("Результат: ").append(value(mission.getOutcome())).append("\n");

        if (mission.getDamageCost() != null) {
            sb.append("Ущерб: ").append(mission.getDamageCost()).append("\n");
        }

        if (mission.getCurse() != null) {
            sb.append("\n");
            sb.append("Проклятие:\n");
            sb.append("  Название: ").append(value(mission.getCurse().getName())).append("\n");
            sb.append("  Уровень угрозы: ").append(value(mission.getCurse().getThreatLevel())).append("\n");
        }

        if (mission.getSorcerers() != null && !mission.getSorcerers().isEmpty()) {
            sb.append("\n");
            sb.append("Участники:\n");
            for (Sorcerer s : mission.getSorcerers()) {
                sb.append("  - Имя: ").append(value(s.getName())).append("\n");
                sb.append("    Ранг: ").append(value(s.getRank())).append("\n");
            }
        }

        if (mission.getTechniques() != null && !mission.getTechniques().isEmpty()) {
            sb.append("\n");
            sb.append("Техники:\n");
            for (Technique t : mission.getTechniques()) {
                sb.append("  - Название: ").append(value(t.getName())).append("\n");
                sb.append("    Тип: ").append(value(t.getType())).append("\n");
                sb.append("    Владелец: ").append(value(t.getOwner())).append("\n");
                if (t.getDamage() != null) {
                    sb.append("    Урон: ").append(t.getDamage()).append("\n");
                }
            }
        }

        if (mission.getEconomicAssessment() != null) {
            EconomicAssessment ea = mission.getEconomicAssessment();
            sb.append("\n");
            sb.append("Экономическая оценка:\n");
            if (ea.getTotalDamageCost() != null) {
                sb.append("  Общий ущерб: ").append(ea.getTotalDamageCost()).append("\n");
            }
            if (ea.getInfrastructureDamage() != null) {
                sb.append("  Ущерб инфраструктуре: ").append(ea.getInfrastructureDamage()).append("\n");
            }
            if (ea.getTransportDamage() != null) {
                sb.append("  Ущерб транспорту: ").append(ea.getTransportDamage()).append("\n");
            }
            if (ea.getCommercialDamage() != null) {
                sb.append("  Коммерческий ущерб: ").append(ea.getCommercialDamage()).append("\n");
            }
            if (ea.getRecoveryEstimateDays() != null) {
                sb.append("  Срок восстановления (дней): ").append(ea.getRecoveryEstimateDays()).append("\n");
            }
            if (ea.getInsuranceCovered() != null) {
                sb.append("  Покрыто страховкой: ").append(ea.getInsuranceCovered()).append("\n");
            }
        }

        if (mission.getCivilianImpact() != null) {
            CivilianImpact ci = mission.getCivilianImpact();
            sb.append("\n");
            sb.append("Влияние на гражданских:\n");
            if (ci.getEvacuated() != null) {
                sb.append("  Эвакуировано: ").append(ci.getEvacuated()).append("\n");
            }
            if (ci.getInjured() != null) {
                sb.append("  Пострадавшие: ").append(ci.getInjured()).append("\n");
            }


            if (ci.getMissing() != null) {
                sb.append("  Пропавшие: ").append(ci.getMissing()).append("\n");
            }
            if (ci.getPublicExposureRisk() != null) {
                sb.append("  Риск раскрытия: ").append(ci.getPublicExposureRisk()).append("\n");
            }
        }

        if (mission.getEnemyActivity() != null) {
            EnemyActivity ea = mission.getEnemyActivity();
            sb.append("\n");
            sb.append("Активность противника:\n");
            if (ea.getBehaviorType() != null) {
                sb.append("  Тип поведения: ").append(ea.getBehaviorType()).append("\n");
            }
            if (ea.getTargetPriority() != null && !ea.getTargetPriority().isEmpty()) {
                sb.append("  Приоритет целей:\n");
                for (String p : ea.getTargetPriority()) {
                    sb.append("    - ").append(p).append("\n");
                }
            }
            if (ea.getAttackPatterns() != null && !ea.getAttackPatterns().isEmpty()) {
                sb.append("  Паттерны атак:\n");
                for (String p : ea.getAttackPatterns()) {
                    sb.append("    - ").append(p).append("\n");
                }
            }
            if (ea.getMobility() != null) {
                sb.append("  Мобильность: ").append(ea.getMobility()).append("\n");
            }
            if (ea.getEscalationRisk() != null) {
                sb.append("  Риск эскалации: ").append(ea.getEscalationRisk()).append("\n");
            }
            if (ea.getCountermeasuresUsed() != null && !ea.getCountermeasuresUsed().isEmpty()) {
                sb.append("  Применённые контрмеры:\n");
                for (String m : ea.getCountermeasuresUsed()) {
                    sb.append("    - ").append(m).append("\n");
                }
            }
        }

        if (mission.getEnvironmentConditions() != null) {
            EnvironmentConditions ec = mission.getEnvironmentConditions();
            sb.append("\n");
            sb.append("Условия среды:\n");
            if (ec.getWeather() != null) {
                sb.append("  Погода: ").append(ec.getWeather()).append("\n");
            }
            if (ec.getTimeOfDay() != null) {
                sb.append("  Время суток: ").append(ec.getTimeOfDay()).append("\n");
            }
            if (ec.getVisibility() != null) {
                sb.append("  Видимость: ").append(ec.getVisibility()).append("\n");
            }
            if (ec.getCursedEnergyDensity() != null) {
                sb.append("  Плотность проклятой энергии: ").append(ec.getCursedEnergyDensity()).append("\n");
            }
        }

        if (mission.getOperationTimeline() != null && !mission.getOperationTimeline().isEmpty()) {
            sb.append("\n");
            sb.append("Хронология операции:\n");
            for (TimelineEvent event : mission.getOperationTimeline()) {
                sb.append("  - Время: ").append(value(event.getTimestamp())).append("\n");
                sb.append("    Тип: ").append(value(event.getType())).append("\n");
                sb.append("    Описание: ").append(value(event.getDescription())).append("\n");
            }
        }

        if (mission.getOperationTags() != null && !mission.getOperationTags().isEmpty()) {
            sb.append("\n");
            sb.append("Теги операции:\n");
            for (String tag : mission.getOperationTags()) {
                sb.append("  - ").append(tag).append("\n");
            }
        }

        if (mission.getSupportUnits() != null && !mission.getSupportUnits().isEmpty()) {
            sb.append("\n");
            sb.append("Подразделения поддержки:\n");
            for (String unit : mission.getSupportUnits()) {
                sb.append("  - ").append(unit).append("\n");
            }
        }

        if (mission.getRecommendations() != null && !mission.getRecommendations().isEmpty()) {
            sb.append("\n");
            sb.append("Рекомендации:\n");
            for (String recommendation : mission.getRecommendations()) {


                sb.append("  - ").append(recommendation).append("\n");
            }
        }

        if (mission.getNotes() != null && !mission.getNotes().isBlank()) {
            sb.append("\n");
            sb.append("Примечание:\n");
            sb.append("  ").append(mission.getNotes()).append("\n");
        }

        if (mission.getArtifactsRecovered() != null && !mission.getArtifactsRecovered().isEmpty()) {
            sb.append("\n");
            sb.append("Изъятые артефакты:\n");
            for (String artifact : mission.getArtifactsRecovered()) {
                sb.append("  - ").append(artifact).append("\n");
            }
        }

        if (mission.getEvacuationZones() != null && !mission.getEvacuationZones().isEmpty()) {
            sb.append("\n");
            sb.append("Зоны эвакуации:\n");
            for (String zone : mission.getEvacuationZones()) {
                sb.append("  - ").append(zone).append("\n");
            }
        }

        if (mission.getStatusEffects() != null && !mission.getStatusEffects().isEmpty()) {
            sb.append("\n");
            sb.append("Статусные эффекты:\n");
            for (String effect : mission.getStatusEffects()) {
                sb.append("  - ").append(effect).append("\n");
            }
        }

        return sb.toString();
    }

    private String value(Object value) {
        return value == null ? "-" : String.valueOf(value);
    }
}