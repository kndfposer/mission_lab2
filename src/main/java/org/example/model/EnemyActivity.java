package org.example.model;

import jakarta.persistence.*;
import org.example.model.enums.EscalationRisk;
import org.example.model.enums.Mobility;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enemy_activity")
public class EnemyActivity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String behaviorType;
    @ElementCollection
    @CollectionTable(name = "enemy_target_priority", joinColumns = @JoinColumn(name = "enemy_id_fk"))
    @Column(name = "value")
    private List<String> targetPriority = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "enemy_attack_patterns", joinColumns = @JoinColumn(name = "enemy_id_fk"))
    @Column(name = "value")
    private List<String> attackPatterns = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Mobility mobility = Mobility.UNKNOWN;
    @Enumerated(EnumType.STRING)
    private EscalationRisk escalationRisk = EscalationRisk.UNKNOWN;
    @ElementCollection
    @CollectionTable(name = "enemy_countermeasures", joinColumns = @JoinColumn(name = "enemy_id_fk"))
    @Column(name = "value")
    private List<String> countermeasuresUsed = new ArrayList<>();
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
