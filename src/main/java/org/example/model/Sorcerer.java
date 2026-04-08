package org.example.model;

import org.example.model.enums.SorcererRank;

public class Sorcerer {
    private String name;
    private SorcererRank rank = SorcererRank.UNKNOWN;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public SorcererRank getRank() { return rank; }
    public void setRank(SorcererRank rank) { this.rank = rank; }
}
