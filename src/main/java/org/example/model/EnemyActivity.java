package org.example.model;

import org.example.model.enums.EscalationRisk;
import org.example.model.enums.Mobility;

import java.util.ArrayList;
import java.util.List;

public class EnemyActivity {
    private String behaviorType;
    private final List<String> targetPriority = new ArrayList<>();
    private final List<String> attackPatterns = new ArrayList<>();
    private Mobility mobility = Mobility.UNKNOWN;
    private EscalationRisk escalationRisk = EscalationRisk.UNKNOWN;
    private final List<String> countermeasuresUsed = new ArrayList<>();

    public String getBehaviorType() { return behaviorType; }
    public void setBehaviorType(String behaviorType) { this.behaviorType = behaviorType; }
    public List<String> getTargetPriority() { return targetPriority; }
    public List<String> getAttackPatterns() { return attackPatterns; }
    public Mobility getMobility() { return mobility; }
    public void setMobility(Mobility mobility) { this.mobility = mobility; }
    public EscalationRisk getEscalationRisk() { return escalationRisk; }
    public void setEscalationRisk(EscalationRisk escalationRisk) { this.escalationRisk = escalationRisk; }
    public List<String> getCountermeasuresUsed() { return countermeasuresUsed; }
}
