package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Mission;
import org.example.persistence.MissionRecordStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveShelfService {
    private final MissionRecordStore missionRecordStore;

    public ArchiveShelfService(MissionRecordStore missionRecordStore) {
        this.missionRecordStore = missionRecordStore;
    }

    @Transactional
    public Mission saveFresh(Mission mission) {
        if (missionRecordStore.findByMissionId(mission.getMissionId()).isPresent()) {
            throw new IllegalArgumentException("Миссия с данным id уже присутствует в архиве");
        }
        return missionRecordStore.save(mission);
    }

    @Transactional
    public List<Mission> fetchAll() {
        return missionRecordStore.findAll();
    }

    @Transactional
    public Mission fetchOneDetailed(String missionId) {
        Mission mission = missionRecordStore.findByMissionId(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Миссия не найдена: " + missionId));
        if (mission.getCurse() != null) mission.getCurse().getName();
        mission.getSorcerers().size();
        mission.getTechniques().size();
        mission.getOperationTimeline().size();
        mission.getOperationTags().size();
        mission.getSupportUnits().size();
        mission.getRecommendations().size();
        mission.getArtifactsRecovered().size();
        mission.getEvacuationZones().size();
        mission.getStatusEffects().size();
        if (mission.getEconomicAssessment() != null) mission.getEconomicAssessment().getTotalDamageCost();
        if (mission.getCivilianImpact() != null) mission.getCivilianImpact().getEvacuated();
        if (mission.getEnemyActivity() != null) {
            mission.getEnemyActivity().getBehaviorType();
            mission.getEnemyActivity().getTargetPriority().size();
            mission.getEnemyActivity().getAttackPatterns().size();
            mission.getEnemyActivity().getCountermeasuresUsed().size();
        }
        if (mission.getEnvironmentConditions() != null) mission.getEnvironmentConditions().getWeather();
        return mission;
    }

    @Transactional
    public void removeOne(String missionId) {
        Mission mission = missionRecordStore.findByMissionId(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Миссия не найдена: " + missionId));
        missionRecordStore.delete(mission);
    }
}
