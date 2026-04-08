package org.example.model;

public class Technique {
    private String name;
    private TechniqueType type;
    private String owner;
    private Long damage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TechniqueType getType() {
        return type;
    }

    public void setType(TechniqueType type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getDamage() {
        return damage;
    }

    public void setDamage(Long damage) {
        this.damage = damage;
    }
}
