package org.example.web.view;

import java.util.List;

public record MissionSnapshotView(
        String missionId,
        String date,
        String location,
        String outcome,
        Long damageCost,
        String curseName,
        String curseThreatLevel,
        List<String> sorcerers,
        List<String> techniques,
        String notes
) {}
