package org.example.validator;

import org.example.model.*;

public class MissionValidator {
    public void validate(Mission mission) {
        require(mission.getMissionId(), "missionId обязателен");
        if (mission.getDate() == null) throw new IllegalArgumentException("date обязателен");
        require(mission.getLocation(), "location обязателен");
        if (mission.getOutcome() == null) throw new IllegalArgumentException("outcome обязателен");
        if (mission.getCurse() == null) throw new IllegalArgumentException("curse обязателен");
        require(mission.getCurse().getName(), "curse.name обязателен");
        if (mission.getCurse().getThreatLevel() == null) throw new IllegalArgumentException("curse.threatLevel обязателен");
        for (Sorcerer s : mission.getSorcerers()) {
            require(s.getName(), "sorcerers.name обязателен, если блок присутствует");
            if (s.getRank() == null) throw new IllegalArgumentException("sorcerers.rank обязателен, если блок присутствует");
        }
        for (Technique t : mission.getTechniques()) {
            require(t.getName(), "techniques.name обязателен, если блок присутствует");
            if (t.getType() == null) throw new IllegalArgumentException("techniques.type обязателен, если блок присутствует");
            require(t.getOwner(), "techniques.owner обязателен, если блок присутствует");
        }
        for (TimelineEvent ev : mission.getOperationTimeline()) {
            if (ev.getTimestamp() == null) throw new IllegalArgumentException("operationTimeline.timestamp обязателен");
            require(ev.getType(), "operationTimeline.type обязателен");
            require(ev.getDescription(), "operationTimeline.description обязателен");
        }
    }
    private void require(String value, String message) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(message);
    }
}
