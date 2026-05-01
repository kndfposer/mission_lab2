package org.example.model;

import jakarta.persistence.*;
import org.example.model.enums.SorcererRank;

@Entity
@Table(name = "sorcerer")
public class Sorcerer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private SorcererRank rank = SorcererRank.UNKNOWN;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public SorcererRank getRank() { return rank; }
    public void setRank(SorcererRank rank) { this.rank = rank; }
}
