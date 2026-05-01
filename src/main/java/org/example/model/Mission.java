package org.example.model;

import jakarta.persistence.*;
import org.example.model.enums.MissionOutcome;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missions")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_id", nullable = false, unique = true)
    private String missionId;
    private LocalDate date;
    private String location;

    @Enumerated(EnumType.STRING)
    private MissionOutcome outcome = MissionOutcome.UNKNOWN;

    @Column(name = "damage_cost")
    private Long damageCost;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Curse curse;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mission_id_fk")
    private List<Sorcerer> sorcerers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mission_id_fk")
    private List<Technique> techniques = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private EconomicAssessment economicAssessment;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CivilianImpact civilianImpact;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private EnemyActivity enemyActivity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private EnvironmentConditions environmentConditions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mission_id_fk")
    private List<TimelineEvent> operationTimeline = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mission_operation_tags", joinColumns = @JoinColumn(name = "mission_id_fk"))
    @Column(name = "tag_value")
    private List<String> operationTags = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mission_support_units", joinColumns = @JoinColumn(name = "mission_id_fk"))
    @Column(name = "unit_value")
    private List<String> supportUnits = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mission_recommendations", joinColumns = @JoinColumn(name = "mission_id_fk"))
    @Column(name = "recommendation_value")
    private List<String> recommendations = new ArrayList<>();

    @Column(length = 5000)
    private String notes;

    @ElementCollection
    @CollectionTable(name = "mission_artifacts_recovered", joinColumns = @JoinColumn(name = "mission_id_fk"))
    @Column(name = "artifact_value")
    private List<String> artifactsRecovered = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mission_evacuation_zones", joinColumns = @JoinColumn(name = "mission_id_fk"))
    @Column(name = "zone_value")
    private List<String> evacuationZones = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mission_status_effects", joinColumns = @JoinColumn(name = "mission_id_fk"))
    @Column(name = "effect_value")
    private List<String> statusEffects = new ArrayList<>();

    public Long getId() { return id; }
    public String getMissionId() { return missionId; }
    public void setMissionId(String missionId) { this.missionId = missionId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public MissionOutcome getOutcome() { return outcome; }
    public void setOutcome(MissionOutcome outcome) { this.outcome = outcome; }
    public Long getDamageCost() { return damageCost; }
    public void setDamageCost(Long damageCost) { this.damageCost = damageCost; }
    public Curse getCurse() { return curse; }
    public void setCurse(Curse curse) { this.curse = curse; }
    public List<Sorcerer> getSorcerers() { return sorcerers; }
    public List<Technique> getTechniques() { return techniques; }
    public EconomicAssessment getEconomicAssessment() { return economicAssessment; }
    public void setEconomicAssessment(EconomicAssessment economicAssessment) { this.economicAssessment = economicAssessment; }
    public CivilianImpact getCivilianImpact() { return civilianImpact; }
    public void setCivilianImpact(CivilianImpact civilianImpact) { this.civilianImpact = civilianImpact; }
    public EnemyActivity getEnemyActivity() { return enemyActivity; }
    public void setEnemyActivity(EnemyActivity enemyActivity) { this.enemyActivity = enemyActivity; }
    public EnvironmentConditions getEnvironmentConditions() { return environmentConditions; }
    public void setEnvironmentConditions(EnvironmentConditions environmentConditions) { this.environmentConditions = environmentConditions; }
    public List<TimelineEvent> getOperationTimeline() { return operationTimeline; }
    public List<String> getOperationTags() { return operationTags; }
    public List<String> getSupportUnits() { return supportUnits; }
    public List<String> getRecommendations() { return recommendations; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public List<String> getArtifactsRecovered() { return artifactsRecovered; }
    public List<String> getEvacuationZones() { return evacuationZones; }
    public List<String> getStatusEffects() { return statusEffects; }
}
