package com.example.pokedexls;

public class Pokeball {
    private String imageResource;
    private String type;
    private int quantity;
    private float multiplier;

    public Pokeball(String imageResource, String type, int quantity, float multiplier) {
        this.imageResource = imageResource;
        this.type = type;
        this.quantity = quantity;
        this.multiplier = multiplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public int getQuantity() {
        return quantity;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }
}
