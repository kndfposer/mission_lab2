package org.example.report;

import org.example.model.Mission;

public class RiskMissionReportStrategy implements MissionReportStrategy {
    @Override
    public String createReport(Mission mission) {
        StringBuilder sb = new StringBuilder();
        sb.append("ОТЧЁТ ПО РИСКАМ\n");
        sb.append("missionId: ").append(mission.getMissionId()).append('\n');
        sb.append("threatLevel: ").append(mission.getCurse().getThreatLevel()).append('\n');
        if (mission.getEnemyActivity() != null) {
            if (mission.getEnemyActivity().getMobility() != null) {
                sb.append("enemy mobility: ").append(mission.getEnemyActivity().getMobility()).append('\n');
            }
            if (mission.getEnemyActivity().getEscalationRisk() != null) {
                sb.append("escalationRisk: ").append(mission.getEnemyActivity().getEscalationRisk()).append('\n');
            }
        }
        if (mission.getCivilianImpact() != null && mission.getCivilianImpact().getPublicExposureRisk() != null) {
            sb.append("publicExposureRisk: ").append(mission.getCivilianImpact().getPublicExposureRisk()).append('\n');
        }
        return sb.toString();
    }
}
