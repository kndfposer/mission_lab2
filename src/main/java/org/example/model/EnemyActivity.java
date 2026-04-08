package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class EnemyActivity {
    private String behaviorType;
    private List<String> targetPriority = new ArrayList<>();
    private List<String> attackPatterns = new ArrayList<>();
    private List<String> countermeasuresUsed = new ArrayList<>();
    private MobilityLevel mobility;
    private EscalationRisk escalationRisk;

    public String getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(String behaviorType) {
        this.behaviorType = behaviorType;
    }

    public List<String> getTargetPriority() {
        return targetPriority;
    }

    public void setTargetPriority(List<String> targetPriority) {
        this.targetPriority = targetPriority;
    }

    public List<String> getAttackPatterns() {
        return attackPatterns;
    }

    public void setAttackPatterns(List<String> attackPatterns) {
        this.attackPatterns = attackPatterns;
    }

    public List<String> getCountermeasuresUsed() {
        return countermeasuresUsed;
    }

    public void setCountermeasuresUsed(List<String> countermeasuresUsed) {
        this.countermeasuresUsed = countermeasuresUsed;
    }

    public MobilityLevel getMobility() {
        return mobility;
    }

    public void setMobility(MobilityLevel mobility) {
        this.mobility = mobility;
    }

    public EscalationRisk getEscalationRisk() {
        return escalationRisk;
    }

    public void setEscalationRisk(EscalationRisk escalationRisk) {
        this.escalationRisk = escalationRisk;
    }
}
