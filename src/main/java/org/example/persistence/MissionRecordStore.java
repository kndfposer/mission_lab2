package org.example.persistence;

import org.example.model.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionRecordStore extends JpaRepository<Mission, Long> {
    Optional<Mission> findByMissionId(String missionId);
}
