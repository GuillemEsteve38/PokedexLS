package com.example.pokedexls;


import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.AsyncListUtil;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedexls.Pokemon;
import com.example.pokedexls.Stats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.security.auth.callback.Callback;

public class PokemonLab{

    private static PokemonLab sCrimeLab;

    private static ArrayList<Pokemon> mCrimes;
    private static int offset;
    private Context context;

    public static PokemonLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new PokemonLab(context);
            mCrimes = new ArrayList<>();
            offset = 0;
        }

        return sCrimeLab;
    }

    public PokemonLab(Context context) {
        this.context = context;
        mCrimes = new ArrayList<>();

    }

    public void pokemonList(DataCallback callback,int offset) {

        //for (int i = 1; i < 16; i++) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "https://pokeapi.co/api/v2/pokemon" + "?offset=" + offset + "&limit=15";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray results = response.getJSONArray( "results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject pokemon = results.getJSONObject(i);
                        String name = pokemon.getString("name");
                        String url1 = pokemon.getString("url");
                        getpokemon(callback, url1);

                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(view.getContext(), "Fail to get the pokemon", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);


    }
    public  ArrayList<Pokemon> getpokemon(DataCallback callback, String url) {
        ArrayList<Pokemon> a = new ArrayList<>();
        //for (int i = 1; i < 16; i++) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.println(Log.DEBUG, "aaaa", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int id = response.getInt("id");
                    String name = response.getString("name");
                    JSONArray type = response.getJSONArray("types");
                    ArrayList<String> types = new ArrayList<>();
                    for (int i = 0; i < type.length(); i++) {
                        types.add(type.getJSONObject(i).getJSONObject("type").getString("name"));
                    }

                    String img_front = response.getJSONObject("sprites").getString("front_default");
                    String img_back = response.getJSONObject("sprites").getString("back_default");
                    Random rnd = new Random();
                    int prob = rnd.nextInt(501)+1;
                    if (prob == 1){
                        img_front = response.getJSONObject("sprites").getString("front_shiny");
                        img_back = response.getJSONObject("sprites").getString("back_shiny");

                    }
                    //Toast.makeText(view.getContext(), img, Toast.LENGTH_SHORT).show();
                    ArrayList<String> images = new ArrayList<>();
                    images.add(img_front);
                    images.add(img_back);
                    JSONArray stat = response.getJSONArray("stats");
                    ArrayList<Stats> stats = new ArrayList<>();
                    for (int i = 0; i < stat.length(); i++) {
                        String a = stat.getJSONObject(i).getString("base_stat");
                        stats.add(new Stats(stat.getJSONObject(i).getJSONObject("stat").getString("name"), Integer.parseInt(stat.getJSONObject(i).getString("base_stat"))));
                    }
                    JSONArray abil = response.getJSONArray("abilities");
                    ArrayList<Ability> abilities = new ArrayList<>();
                    for (int i = 0; i < abil.length(); i++) {
                        //String a = stat.getJSONObject(i).getString("ability");
                        abilities.add(new Ability(abil.getJSONObject(i).getJSONObject("ability").getString("name"), Boolean.parseBoolean(abil.getJSONObject(i).getString("is_hidden"))));
                    }
                    mCrimes.add(new Pokemon(id,name,"aaa" ,abilities,types, images,stats));
                    mCrimes.sort(Comparator.comparing(Pokemon::getId));
                    callback.onSuccess(mCrimes);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(view.getContext(), "Fail to get the pokemon", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

        return a;
    }
    public List<Pokemon> getChat() {
        return mCrimes;
    }

    public static PokemonLab getsCrimeLab() {
        return sCrimeLab;
    }

    public Pokemon getChat(int id) {
        for (Pokemon crime : mCrimes) {
            if (crime.getId() == id) {
                return crime;
            }
        }

        return null;
    }

    public void getDesc(String id , DataCallback callback) {
       ArrayList<String> a = new ArrayList<>();
        //for (int i = 1; i < 16; i++) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "https://pokeapi.co/api/v2/pokemon-species/" + id;
        Log.println(Log.DEBUG, "aaaa", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray type = response.getJSONArray("flavor_text_entries");
                    ArrayList<String> types = new ArrayList<>();
                    for (int i = 0; i < type.length(); i++) {
                        if (type.getJSONObject(i).getJSONObject("language").getString("name").equals("en")){
                            a.add(type.getJSONObject(i).getString("flavor_text"));
                            callback.onSuccess(type.getJSONObject(i).getString("flavor_text"));
                        }

                    }
                    boolean legend = response.getBoolean("is_legendary");
                    if (legend){
                        mCrimes.get(Integer.parseInt(id)-1).setLegendary(legend);
                        mCrimes.get(Integer.parseInt(id)-1).setEvolution(4);
                    }else{
                        evolutionChain(response.getJSONObject("evolution_chain").getString("url"),id);
                    }


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(view.getContext(), "Fail to get the pokemon", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    private void evolutionChain(String url, String id){
        ArrayList<String> a = new ArrayList<>();
        //for (int i = 1; i < 16; i++) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        Log.println(Log.DEBUG, "aaaa", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 1;
                try {
                    JSONObject chain = response.getJSONObject("chain");

                    JSONArray type = chain.getJSONArray("evolves_to");
                    if (!chain.getJSONObject("species").getString("name").equals(getChat(Integer.parseInt(id)).getName())) {

                        for (int i = 0; i < type.length(); i++) {
                            JSONObject first = type.getJSONObject(i).getJSONObject("species");
                            count++;
                            if (!first.getString("name").equals(getChat(Integer.parseInt(id)).getName())) {
                                JSONArray evolves = type.getJSONObject(i).getJSONArray("evolves_to");
                                for (int j = 0; j < evolves.length(); j++) {
                                    JSONObject second = type.getJSONObject(i).getJSONObject("species");
                                    if (!second.getString("name").equals(getChat(Integer.parseInt(id)).getName())) {

                                        count++;
                                    }
                                }
                            }


                        }
                    }
                    getChat(Integer.parseInt(id)).setEvolution(count);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(view.getContext(), "Fail to get the pokemon", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offse) {
        offset += offse;
    }
    public void clearOffset() {
        offset = 0;
    }
    public static void clearList(){
        mCrimes.clear();
    }
    public interface DataCallback {
        void onSuccess(List<Pokemon> dataList);
        void onSuccess(String dataList);
        void onFailure(String errorMessage);
    }

}

