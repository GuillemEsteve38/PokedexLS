package com.example.pokedexls;

import java.util.List;

public interface ApiResponseCallback {
    void onDataReceived(List<Pokemon> data);
}