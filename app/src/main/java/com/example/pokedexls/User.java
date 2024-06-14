package com.example.pokedexls;

import android.content.Context;

import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    private static String name;
    private static int money;
    private static ArrayList<Pokeball> pokeballs;
    private static ArrayList<Pokemon> pokemons;

    public User(String name, int money, ArrayList<Pokeball> pokeballs, ArrayList<Pokemon> pokemons) {
        User.name = name;
        User.money = money;
        User.pokeballs = pokeballs;
        User.pokemons = pokemons;
    }


    public static String getName() { return name; }
    public static void setName(String name) { User.name = name; }

    public static int getMoney() { return money; }
    public static void setMoney(int money) { User.money = money; }

    public static ArrayList<Pokeball> getPokeballs() { return pokeballs; }
    public static void setPokeballs(ArrayList<Pokeball> pokeballs) { User.pokeballs = pokeballs; }

    public static ArrayList<Pokemon> getPokemons() { return pokemons; }
    public static void setPokemons(ArrayList<Pokemon> pokemons) { User.pokemons = pokemons; }

    public static void saveUserData(Context context,InputStream inputStream) {
        JSONObject user = null;


            user = loadUserData(inputStream);

        if (user != null) {
            try {
                // Modify User data as needed
                user.put("name", getName());
                user.put("money", getMoney());

                JSONArray pokeballs = new JSONArray();
                JSONObject pokeball1 = new JSONObject();
                pokeball1.put("type", getPokeballs().get(0).getType());
                pokeball1.put("quantity", getPokeballs().get(0).getQuantity());
                pokeball1.put("multiplier", getPokeballs().get(0).getMultiplier());
                pokeball1.put("img", getPokeballs().get(0).getImageResource());

                JSONObject pokeball2 = new JSONObject();
                pokeball2.put("type", getPokeballs().get(1).getType());
                pokeball2.put("quantity", getPokeballs().get(1).getQuantity());
                pokeball2.put("multiplier", getPokeballs().get(1).getMultiplier());
                pokeball2.put("img", getPokeballs().get(1).getImageResource());
                JSONObject pokeball3 = new JSONObject();
                pokeball3.put("type", getPokeballs().get(2).getType());
                pokeball3.put("quantity", getPokeballs().get(2).getQuantity());
                pokeball3.put("multiplier", getPokeballs().get(2).getMultiplier());
                pokeball3.put("img", getPokeballs().get(2).getImageResource());
                JSONObject pokeball4 = new JSONObject();
                pokeball4.put("type", getPokeballs().get(3).getType());
                pokeball4.put("quantity", getPokeballs().get(3).getQuantity());
                pokeball4.put("multiplier", getPokeballs().get(3).getMultiplier());
                pokeball4.put("img", getPokeballs().get(3).getImageResource());
                pokeballs.put(pokeball1);
                pokeballs.put(pokeball2);
                pokeballs.put(pokeball3);
                pokeballs.put(pokeball4);

                JSONArray pokemons = new JSONArray();
                for (Pokemon p : getPokemons()) {
                    JSONObject pokemon1 = new JSONObject();
                    pokemon1.put("name", p.getName());
                    pokemon1.put("img", p.getImg().get(0));
                    pokemon1.put("pokeball", p.getPokeball());
                    pokemons.put(pokemon1);
                }


                user.put("pokeballs", pokeballs);
                user.put("pokemons", pokemons);

                FileOutputStream outputStream = context.openFileOutput("persistence.json", Context.MODE_PRIVATE);
                outputStream.write(user.toString().getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public static JSONObject  loadUserData(InputStream inputStream)  {
        Gson gson = new Gson();
        String jsonString = "";
        byte[] data = new byte[0];
        JSONObject user = null;
        try {

            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            jsonString = new String(data,"UTF-8");
            user = new JSONObject(jsonString);

            inputStream.close();
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

        //User u = new User(user.getName(),user.getMoney(),user.getPokeballs(),user.getPokemons());
        return user;
    }

    public static void storeData(JSONObject user){
        try {
            User.setName(user.getString("name"));
            User.setMoney(user.getInt("money"));
            JSONArray a = user.getJSONArray("pokeballs");
            ArrayList<Pokeball> pokeballs = new ArrayList<>();
            for (int i = 0; i < a.length(); i++) {
                //String a = stat.getJSONObject(i).getString("ability");
                int img = 0;

                pokeballs.add(new Pokeball(a.getJSONObject(i).getString("img"),a.getJSONObject(i).getString("type"),a.getJSONObject(i).getInt("quantity"), (float) a.getJSONObject(i).getDouble("multiplier")));
            }

            JSONArray b = user.getJSONArray("pokemons");
            ArrayList<Pokemon> pokemons = new ArrayList<>();
            for (int i = 0; i < b.length(); i++) {
                //String a = stat.getJSONObject(i).getString("ability");
                pokemons.add(new Pokemon(b.getJSONObject(i).getString("name"),b.getJSONObject(i).getString("img"),b.getJSONObject(i).getString("pokeball")));
            }
            User.setPokeballs(pokeballs);
            User.setPokemons(pokemons);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getPokeball(String name){
        for (Pokeball p: pokeballs) {
            if (Objects.equals(p.getType(), name)){
                return p.getImageResource();
            }
        }
        return "";
    }


}
