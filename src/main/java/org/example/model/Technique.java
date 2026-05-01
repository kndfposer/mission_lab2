package org.example.model;

import jakarta.persistence.*;
import org.example.model.enums.TechniqueType;

@Entity
@Table(name = "technique")
public class Technique {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TechniqueType type = TechniqueType.UNKNOWN;
    private String owner;
    private Long damage;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public TechniqueType getType() { return type; }
    public void setType(TechniqueType type) { this.type = type; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    public Long getDamage() { return damage; }
    public void setDamage(Long damage) { this.damage = damage; }
}
