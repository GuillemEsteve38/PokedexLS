package com.example.pokedexls;

public class Stats {

    private String name;
    private int stat;

    public Stats(String name, int stat) {
        this.name = name;
        this.stat = stat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}
