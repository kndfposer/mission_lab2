package org.example.web.adapter;

import org.example.model.Mission;
import org.example.web.view.MissionRowView;
import org.example.web.view.MissionSnapshotView;

import java.util.List;

public final class MissionCardAssembler {
    private MissionCardAssembler() {}

    public static MissionRowView toRow(Mission mission) {
        return new MissionRowView(
                mission.getMissionId(),
                mission.getDate() == null ? null : mission.getDate().toString(),
                mission.getLocation(),
                mission.getOutcome() == null ? null : mission.getOutcome().name()
        );
    }

    public static MissionSnapshotView toSnapshot(Mission mission) {
        List<String> sorcerers = mission.getSorcerers().stream()
                .map(s -> s.getName() + " (" + (s.getRank() == null ? null : s.getRank().name()) + ")")
                .toList();
        List<String> techniques = mission.getTechniques().stream()
                .map(t -> t.getName() + " | " + (t.getType() == null ? null : t.getType().name())
                        + " | owner=" + t.getOwner() + " | damage=" + t.getDamage())
                .toList();
        return new MissionSnapshotView(
                mission.getMissionId(),
                mission.getDate() == null ? null : mission.getDate().toString(),
                mission.getLocation(),
                mission.getOutcome() == null ? null : mission.getOutcome().name(),
                mission.getDamageCost(),
                mission.getCurse() == null ? null : mission.getCurse().getName(),
                mission.getCurse() == null || mission.getCurse().getThreatLevel() == null ? null : mission.getCurse().getThreatLevel().name(),
                sorcerers,
                techniques,
                mission.getNotes()
        );
    }
}
