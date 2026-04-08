package org.example.report;

import org.example.model.*;

public class DetailedMissionReportStrategy implements MissionReportStrategy {
    @Override
    public String createReport(Mission mission) {
        StringBuilder sb = new StringBuilder(new SummaryMissionReportStrategy().createReport(mission));

        if (mission.getEconomicAssessment() != null) {
            EconomicAssessment e = mission.getEconomicAssessment();
            sb.append("\nЭкономическая оценка:\n");
            if (e.getTotalDamageCost() != null) sb.append("- totalDamageCost: ").append(e.getTotalDamageCost()).append('\n');
            if (e.getInfrastructureDamage() != null) sb.append("- infrastructureDamage: ").append(e.getInfrastructureDamage()).append('\n');
            if (e.getCommercialDamage() != null) sb.append("- commercialDamage: ").append(e.getCommercialDamage()).append('\n');
            if (e.getTransportDamage() != null) sb.append("- transportDamage: ").append(e.getTransportDamage()).append('\n');
            if (e.getRecoveryEstimateDays() != null) sb.append("- recoveryEstimateDays: ").append(e.getRecoveryEstimateDays()).append('\n');
            if (e.getInsuranceCovered() != null) sb.append("- insuranceCovered: ").append(e.getInsuranceCovered()).append('\n');
        }

        if (mission.getCivilianImpact() != null) {
            CivilianImpact c = mission.getCivilianImpact();
            sb.append("\nВлияние на гражданских:\n");
            if (c.getEvacuated() != null) sb.append("- evacuated: ").append(c.getEvacuated()).append('\n');
            if (c.getInjured() != null) sb.append("- injured: ").append(c.getInjured()).append('\n');
            if (c.getMissing() != null) sb.append("- missing: ").append(c.getMissing()).append('\n');
            if (c.getPublicExposureRisk() != null) sb.append("- publicExposureRisk: ").append(c.getPublicExposureRisk()).append('\n');
        }

        if (mission.getEnemyActivity() != null) {
            EnemyActivity e = mission.getEnemyActivity();
            sb.append("\nАктивность противника:\n");
            if (e.getBehaviorType() != null) sb.append("- behaviorType: ").append(e.getBehaviorType()).append('\n');
            if (!e.getTargetPriority().isEmpty()) sb.append("- targetPriority: ").append(e.getTargetPriority()).append('\n');
            if (!e.getAttackPatterns().isEmpty()) sb.append("- attackPatterns: ").append(e.getAttackPatterns()).append('\n');
            if (!e.getCountermeasuresUsed().isEmpty()) sb.append("- countermeasuresUsed: ").append(e.getCountermeasuresUsed()).append('\n');
            if (e.getMobility() != null) sb.append("- mobility: ").append(e.getMobility()).append('\n');
            if (e.getEscalationRisk() != null) sb.append("- escalationRisk: ").append(e.getEscalationRisk()).append('\n');
        }

        if (mission.getEnvironmentConditions() != null) {
            EnvironmentConditions env = mission.getEnvironmentConditions();
            sb.append("\nУсловия среды:\n");
            if (env.getWeather() != null) sb.append("- weather: ").append(env.getWeather()).append('\n');
            if (env.getTimeOfDay() != null) sb.append("- timeOfDay: ").append(env.getTimeOfDay()).append('\n');
            if (env.getVisibility() != null) sb.append("- visibility: ").append(env.getVisibility()).append('\n');
            if (env.getCursedEnergyDensity() != null) sb.append("- cursedEnergyDensity: ").append(env.getCursedEnergyDensity()).append('\n');
        }

        if (!mission.getOperationTimeline().isEmpty()) {
            sb.append("\nХронология:\n");
            for (TimelineEvent event : mission.getOperationTimeline()) {
                sb.append("- ").append(event.getTimestamp())
                        .append(" | ").append(event.getType())
                        .append(" | ").append(event.getDescription())
                        .append('\n');
            }
        }

        appendList(sb, "Теги", mission.getOperationTags());
        appendList(sb, "Подразделения поддержки", mission.getSupportUnits());
        appendList(sb, "Рекомендации", mission.getRecommendations());
        appendList(sb, "Найденные артефакты", mission.getArtifactsRecovered());
        appendList(sb, "Зоны эвакуации", mission.getEvacuationZones());
        appendList(sb, "Эффекты/состояния", mission.getStatusEffects());
        return sb.toString();
    }

    private void appendList(StringBuilder sb, String title, java.util.List<String> list) {
        if (!list.isEmpty()) {
            sb.append("\n").append(title).append(":\n");
            for (String item : list) {
                sb.append("- ").append(item).append('\n');
            }
        }
    }
}
