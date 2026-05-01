package org.example.model;

import jakarta.persistence.*;
import org.example.model.enums.ThreatLevel;

@Entity
@Table(name = "curse")
public class Curse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ThreatLevel threatLevel = ThreatLevel.UNKNOWN;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ThreatLevel getThreatLevel() { return threatLevel; }
    public void setThreatLevel(ThreatLevel threatLevel) { this.threatLevel = threatLevel; }
}

