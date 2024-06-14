package com.example.pokedexls;


import java.util.ArrayList;

public class Pokemon {
    private int id;
    private String name;
    private  String desc;
    private ArrayList<Ability> abilities;
    private ArrayList<String> type;
    private ArrayList<String> img;
    private ArrayList<Stats> stats;
    private int evolution;
    private String pokeball;
    private boolean isLegendary;

    public Pokemon(int id, String name, String desc, ArrayList<Ability> abilities, ArrayList<String> type, ArrayList<String> img, ArrayList<Stats> stats) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.abilities = abilities;
        this.type = type;
        this.img = img;
        this.stats = stats;
    }

    public Pokemon(String name, ArrayList<String> type, ArrayList<String> img) {
        this.name = name;
        this.type = type;
        this.img = img;
    }

    public Pokemon(int id, String name, ArrayList<String> type, ArrayList<String> img) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.img = img;
    }

    public Pokemon(String name,String img, String pokeball) {
        this.name = name;
        this.img = new ArrayList<>();
        this.img.add(img);
        this.pokeball = pokeball;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public ArrayList<String> getImg() {
        return img;
    }

    public void setImg(ArrayList<String> img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Stats> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Stats> stats) {
        this.stats = stats;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }

    public String getPokeball() {
        return pokeball;
    }

    public void setPokeball(String pokeball) {
        this.pokeball = pokeball;
    }

    public int getEvolution() {
        return evolution;
    }

    public void setEvolution(int evolution) {
        this.evolution = evolution;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
    }
}

