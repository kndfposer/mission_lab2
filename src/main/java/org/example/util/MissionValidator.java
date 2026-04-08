package org.example.util;


import org.example.model.*;

public final class MissionValidator {
    private MissionValidator() {
    }

    public static void validate(Mission mission) {
        require(mission.getMissionId(), "missionId обязателен");
        if (mission.getDate() == null) {
            throw new IllegalArgumentException("date обязателен");
        }
        require(mission.getLocation(), "location обязателен");
        if (mission.getOutcome() == null) {
            throw new IllegalArgumentException("outcome обязателен");
        }
        if (mission.getCurse() == null) {
            throw new IllegalArgumentException("curse обязателен");
        }
        require(mission.getCurse().getName(), "curse.name обязателен");
        if (mission.getCurse().getThreatLevel() == null) {
            throw new IllegalArgumentException("curse.threatLevel обязателен");
        }

        for (Sorcerer sorcerer : mission.getSorcerers()) {
            require(sorcerer.getName(), "sorcerers.name обязателен, если участник присутствует");
            if (sorcerer.getRank() == null) {
                throw new IllegalArgumentException("sorcerers.rank обязателен, если участник присутствует");
            }
        }

        for (Technique technique : mission.getTechniques()) {
            require(technique.getName(), "techniques.name обязателен, если техника присутствует");
            if (technique.getType() == null) {
                throw new IllegalArgumentException("techniques.type обязателен, если техника присутствует");
            }
            require(technique.getOwner(), "techniques.owner обязателен, если техника присутствует");
        }

        for (TimelineEvent event : mission.getOperationTimeline()) {
            if (event.getTimestamp() == null) {
                throw new IllegalArgumentException("operationTimeline.timestamp обязателен, если событие присутствует");
            }
            require(event.getType(), "operationTimeline.type обязателен, если событие присутствует");
            require(event.getDescription(), "operationTimeline.description обязателен, если событие присутствует");
        }
    }

    private static void require(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
}
