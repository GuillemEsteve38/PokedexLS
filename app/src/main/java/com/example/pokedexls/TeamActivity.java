package com.example.pokedexls;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.FileNotFoundException;

public class TeamActivity extends AppCompatActivity {

    private ImageView ivPokeball1, ivPokeball2, ivPokeball3, ivPokeball4, ivPokeball5, ivPokeball6;
    private LinearLayout innerHorizontalLayout1, innerHorizontalLayout2, innerHorizontalLayout3;
    private LinearLayout innerHorizontalLayout4, innerHorizontalLayout5, innerHorizontalLayout6;

    private ImageView ivPokemon1, ivPokemon2, ivPokemon3, ivPokemon4, ivPokemon5, ivPokemon6;
    private TextView tvPokemon1, tvPokemon2, tvPokemon3, tvPokemon4, tvPokemon5, tvPokemon6;
    private ImageView ivPokeballSelected, ivPokemonSelected;
    private TextView tvName;
    private Button btnRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        // Initialize the ImageViews for pokeballs
        ivPokeball1 = findViewById(R.id.iv_pokeball1);
        ivPokeball1.setVisibility(View.INVISIBLE);
        ivPokeball2 = findViewById(R.id.iv_pokeball2);
        ivPokeball2.setVisibility(View.INVISIBLE);
        ivPokeball3 = findViewById(R.id.iv_pokeball3);
        ivPokeball3.setVisibility(View.INVISIBLE);
        ivPokeball4 = findViewById(R.id.iv_pokeball4);
        ivPokeball4.setVisibility(View.INVISIBLE);
        ivPokeball5 = findViewById(R.id.iv_pokeball5);
        ivPokeball5.setVisibility(View.INVISIBLE);
        ivPokeball6 = findViewById(R.id.iv_pokeball6);
        ivPokeball6.setVisibility(View.INVISIBLE);

        innerHorizontalLayout1 = findViewById(R.id.innerHorizontalLayout1);
        innerHorizontalLayout2 = findViewById(R.id.innerHorizontalLayout2);
        innerHorizontalLayout3 = findViewById(R.id.innerHorizontalLayout3);
        innerHorizontalLayout4 = findViewById(R.id.innerHorizontalLayout4);
        innerHorizontalLayout5 = findViewById(R.id.innerHorizontalLayout5);
        innerHorizontalLayout6 = findViewById(R.id.innerHorizontalLayout6);


        // Initialize the ImageViews for pokemons
        ivPokemon1 = findViewById(R.id.iv_pokemon1);
        ivPokemon1.setVisibility(View.INVISIBLE);
        ivPokemon2 = findViewById(R.id.iv_pokemon2);
        ivPokemon2.setVisibility(View.INVISIBLE);
        ivPokemon3 = findViewById(R.id.iv_pokemon3);
        ivPokemon3.setVisibility(View.INVISIBLE);
        ivPokemon4 = findViewById(R.id.iv_pokemon4);
        ivPokemon4.setVisibility(View.INVISIBLE);
        ivPokemon5 = findViewById(R.id.iv_pokemon5);
        ivPokemon5.setVisibility(View.INVISIBLE);
        ivPokemon6 = findViewById(R.id.iv_pokemon6);
        ivPokemon6.setVisibility(View.INVISIBLE);
        // Initialize the TextViews for pokemons
        tvPokemon1 = findViewById(R.id.tvpokemon1);
        tvPokemon1.setVisibility(View.INVISIBLE);
        tvPokemon2 = findViewById(R.id.tvpokemon2);
        tvPokemon2.setVisibility(View.INVISIBLE);
        tvPokemon3 = findViewById(R.id.tvpokemon3);
        tvPokemon3.setVisibility(View.INVISIBLE);
        tvPokemon4 = findViewById(R.id.tvpokemon4);
        tvPokemon4.setVisibility(View.INVISIBLE);
        tvPokemon5 = findViewById(R.id.tvpokemon5);
        tvPokemon5.setVisibility(View.INVISIBLE);
        tvPokemon6 = findViewById(R.id.tvpokemon6);
        tvPokemon6.setVisibility(View.INVISIBLE);

        // Initialize the ImageViews for selected pokeball and pokemon
        ivPokeballSelected = findViewById(R.id.iv_pokeballSelected);
        ivPokeballSelected.setVisibility(View.INVISIBLE);
        ivPokemonSelected = findViewById(R.id.iv_pokemonSelected);
        ivPokemonSelected.setVisibility(View.INVISIBLE);

        // Initialize the TextView for name
        tvName = findViewById(R.id.tvName);
        tvName.setVisibility(View.INVISIBLE);
        // Initialize the Button
        btnRelease = findViewById(R.id.btnRelease);
        btnRelease.setVisibility(View.INVISIBLE);

        switch (User.getPokemons().size()){
            case 0:
                break;
            case 1:
                pupulateP1();
                break;
            case 2:
                pupulateP1();
                pupulateP2();
                break;
            case 3:
                pupulateP1();
                pupulateP2();
                pupulateP3();
                break;
            case 4:
                pupulateP1();
                pupulateP2();
                pupulateP3();
                pupulateP4();
                break;
            case 5:
                pupulateP1();
                pupulateP2();
                pupulateP3();
                pupulateP4();
                pupulateP5();
                break;
            case 6:
                pupulateP1();
                pupulateP2();
                pupulateP3();
                pupulateP4();
                pupulateP5();
                pupulateP6();
                break;
        }


    }

    private void pupulateP1(){
        ivPokeball1.setVisibility(View.VISIBLE);
        ivPokemon1.setVisibility(View.VISIBLE);
        Glide.with(TeamActivity.this)
                .load(User.getPokeball(User.getPokemons().get(0).getPokeball())).apply(new RequestOptions().override(600, 200))
                .into(ivPokeball1);
        tvPokemon1.setVisibility(View.VISIBLE);
        tvPokemon1.setText(User.getPokemons().get(0).getName());
        Glide.with(TeamActivity.this)
                .load(User.getPokemons().get(0).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                .into(ivPokemon1);

        innerHorizontalLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPokeballSelected.setVisibility(View.VISIBLE);
                ivPokemonSelected.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                btnRelease.setVisibility(View.VISIBLE);
                tvName.setText(User.getPokemons().get(0).getName());

                Glide.with(TeamActivity.this)
                        .load(User.getPokeball(User.getPokemons().get(0).getPokeball())).apply(new RequestOptions().override(600, 200))
                        .into(ivPokeballSelected);

                Glide.with(TeamActivity.this)
                        .load(User.getPokemons().get(0).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                        .into(ivPokemonSelected);
                btnRelease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (User.getPokemons().size() == 1){
                            Toast.makeText(TeamActivity.this,"You must have 1 pokemon in your team.",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TeamActivity.this,"Deleted succesfully.",Toast.LENGTH_SHORT).show();
                            User.getPokemons().remove(0);
                            try {
                                User.saveUserData(TeamActivity.this,TeamActivity.this.openFileInput("persistence.json"));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            ivPokeballSelected.setVisibility(View.INVISIBLE);
                            ivPokemonSelected.setVisibility(View.INVISIBLE);
                            tvName.setVisibility(View.INVISIBLE);
                            btnRelease.setVisibility(View.INVISIBLE);

                            ivPokeball1.setVisibility(View.INVISIBLE);
                            ivPokemon1.setVisibility(View.INVISIBLE);
                            tvPokemon1.setVisibility(View.INVISIBLE);

                        }

                    }
                });
            }
        });
    }
    private void pupulateP2(){
        ivPokeball2.setVisibility(View.VISIBLE);
        ivPokemon2.setVisibility(View.VISIBLE);
        Glide.with(TeamActivity.this)
                .load(User.getPokeball(User.getPokemons().get(1).getPokeball())).apply(new RequestOptions().override(600, 200))
                .into(ivPokeball2);
        tvPokemon2.setVisibility(View.VISIBLE);
        tvPokemon2.setText(User.getPokemons().get(1).getName());
        Glide.with(TeamActivity.this)
                .load(User.getPokemons().get(1).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                .into(ivPokemon2);

        innerHorizontalLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPokeballSelected.setVisibility(View.VISIBLE);
                ivPokemonSelected.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                btnRelease.setVisibility(View.VISIBLE);
                tvName.setText(User.getPokemons().get(1).getName());
                Glide.with(TeamActivity.this)
                        .load(User.getPokeball(User.getPokemons().get(1).getPokeball())).apply(new RequestOptions().override(600, 200))
                        .into(ivPokeballSelected);

                Glide.with(TeamActivity.this)
                        .load(User.getPokemons().get(1).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                        .into(ivPokemonSelected);
                btnRelease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (User.getPokemons().size() == 1){
                            Toast.makeText(TeamActivity.this,"You must have 1 pokemon in your team.",Toast.LENGTH_SHORT).show();
                        }else{

                            Toast.makeText(TeamActivity.this,"Deleted succesfully.",Toast.LENGTH_SHORT).show();
                            User.getPokemons().remove(1);
                            try {
                                User.saveUserData(TeamActivity.this,TeamActivity.this.openFileInput("persistence.json"));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            ivPokeballSelected.setVisibility(View.INVISIBLE);
                            ivPokemonSelected.setVisibility(View.INVISIBLE);
                            tvName.setVisibility(View.INVISIBLE);
                            btnRelease.setVisibility(View.INVISIBLE);

                            ivPokeball2.setVisibility(View.INVISIBLE);
                            ivPokemon2.setVisibility(View.INVISIBLE);
                            tvPokemon2.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }

    private void pupulateP3(){
        ivPokeball3.setVisibility(View.VISIBLE);
        ivPokemon3.setVisibility(View.VISIBLE);
        Glide.with(TeamActivity.this)
                .load(User.getPokeball(User.getPokemons().get(2).getPokeball())).apply(new RequestOptions().override(600, 200))
                .into(ivPokeball3);
        tvPokemon3.setVisibility(View.VISIBLE);
        tvPokemon3.setText(User.getPokemons().get(2).getName());
        Glide.with(TeamActivity.this)
                .load(User.getPokemons().get(2).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                .into(ivPokemon3);
        innerHorizontalLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPokeballSelected.setVisibility(View.VISIBLE);
                ivPokemonSelected.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                btnRelease.setVisibility(View.VISIBLE);
                tvName.setText(User.getPokemons().get(2).getName());
                Glide.with(TeamActivity.this)
                        .load(User.getPokeball(User.getPokemons().get(2).getPokeball())).apply(new RequestOptions().override(600, 200))
                        .into(ivPokeballSelected);

                Glide.with(TeamActivity.this)
                        .load(User.getPokemons().get(2).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                        .into(ivPokemonSelected);
                btnRelease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (User.getPokemons().size() == 1){
                            Toast.makeText(TeamActivity.this,"You must have 1 pokemon in your team.",Toast.LENGTH_SHORT).show();
                        }else{

                            Toast.makeText(TeamActivity.this,"Deleted succesfully.",Toast.LENGTH_SHORT).show();
                            User.getPokemons().remove(2);
                            try {
                                User.saveUserData(TeamActivity.this,TeamActivity.this.openFileInput("persistence.json"));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            ivPokeballSelected.setVisibility(View.INVISIBLE);
                            ivPokemonSelected.setVisibility(View.INVISIBLE);
                            tvName.setVisibility(View.INVISIBLE);
                            btnRelease.setVisibility(View.INVISIBLE);

                            ivPokeball3.setVisibility(View.INVISIBLE);
                            ivPokemon3.setVisibility(View.INVISIBLE);
                            tvPokemon3.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

    }

    private void pupulateP4(){
        ivPokeball4.setVisibility(View.VISIBLE);
        ivPokemon4.setVisibility(View.VISIBLE);
        Glide.with(TeamActivity.this)
                .load(User.getPokeball(User.getPokemons().get(3).getPokeball())).apply(new RequestOptions().override(600, 200))
                .into(ivPokeball4);
        tvPokemon4.setVisibility(View.VISIBLE);
        tvPokemon4.setText(User.getPokemons().get(3).getName());
        Glide.with(TeamActivity.this)
                .load(User.getPokemons().get(3).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                .into(ivPokemon4);

        innerHorizontalLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPokeballSelected.setVisibility(View.VISIBLE);
                ivPokemonSelected.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                btnRelease.setVisibility(View.VISIBLE);
                tvName.setText(User.getPokemons().get(3).getName());
                Glide.with(TeamActivity.this)
                        .load(User.getPokeball(User.getPokemons().get(3).getPokeball())).apply(new RequestOptions().override(600, 200))
                        .into(ivPokeballSelected);

                Glide.with(TeamActivity.this)
                        .load(User.getPokemons().get(3).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                        .into(ivPokemonSelected);
                btnRelease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (User.getPokemons().size() == 1){
                            Toast.makeText(TeamActivity.this,"You must have 1 pokemon in your team.",Toast.LENGTH_SHORT).show();
                        }else{

                            Toast.makeText(TeamActivity.this,"Deleted succesfully.",Toast.LENGTH_SHORT).show();
                            User.getPokemons().remove(3);
                            try {
                                User.saveUserData(TeamActivity.this,TeamActivity.this.openFileInput("persistence.json"));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            ivPokeballSelected.setVisibility(View.INVISIBLE);
                            ivPokemonSelected.setVisibility(View.INVISIBLE);
                            tvName.setVisibility(View.INVISIBLE);
                            btnRelease.setVisibility(View.INVISIBLE);

                            ivPokeball4.setVisibility(View.INVISIBLE);
                            ivPokemon4.setVisibility(View.INVISIBLE);
                            tvPokemon4.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }

    private void pupulateP5(){
        ivPokeball5.setVisibility(View.VISIBLE);
        ivPokemon5.setVisibility(View.VISIBLE);
        Glide.with(TeamActivity.this)
                .load(User.getPokeball(User.getPokemons().get(4).getPokeball())).apply(new RequestOptions().override(600, 200))
                .into(ivPokeball5);
        tvPokemon5.setVisibility(View.VISIBLE);
        tvPokemon5.setText(User.getPokemons().get(4).getName());
        Glide.with(TeamActivity.this)
                .load(User.getPokemons().get(4).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                .into(ivPokemon5);

        innerHorizontalLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPokeballSelected.setVisibility(View.VISIBLE);
                ivPokemonSelected.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                btnRelease.setVisibility(View.VISIBLE);
                tvName.setText(User.getPokemons().get(4).getName());
                Glide.with(TeamActivity.this)
                        .load(User.getPokeball(User.getPokemons().get(4).getPokeball())).apply(new RequestOptions().override(600, 200))
                        .into(ivPokeballSelected);

                Glide.with(TeamActivity.this)
                        .load(User.getPokemons().get(4).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                        .into(ivPokemonSelected);
                btnRelease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (User.getPokemons().size() == 1){
                            Toast.makeText(TeamActivity.this,"You must have 1 pokemon in your team.",Toast.LENGTH_SHORT).show();
                        }else{

                            Toast.makeText(TeamActivity.this,"Deleted succesfully.",Toast.LENGTH_SHORT).show();
                            User.getPokemons().remove(4);
                            try {
                                User.saveUserData(TeamActivity.this,TeamActivity.this.openFileInput("persistence.json"));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            ivPokeballSelected.setVisibility(View.INVISIBLE);
                            ivPokemonSelected.setVisibility(View.INVISIBLE);
                            tvName.setVisibility(View.INVISIBLE);
                            btnRelease.setVisibility(View.INVISIBLE);

                            ivPokeball5.setVisibility(View.INVISIBLE);
                            ivPokemon5.setVisibility(View.INVISIBLE);
                            tvPokemon5.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

    }
    private void pupulateP6(){
        ivPokeball6.setVisibility(View.VISIBLE);
        ivPokemon6.setVisibility(View.VISIBLE);
        Glide.with(TeamActivity.this)
                .load(User.getPokeball(User.getPokemons().get(5).getPokeball())).apply(new RequestOptions().override(600, 200))
                .into(ivPokeball6);
        tvPokemon6.setVisibility(View.VISIBLE);
        tvPokemon6.setText(User.getPokemons().get(5).getName());
        Glide.with(TeamActivity.this)
                .load(User.getPokemons().get(5).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                .into(ivPokemon6);

        innerHorizontalLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPokeballSelected.setVisibility(View.VISIBLE);
                ivPokemonSelected.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                btnRelease.setVisibility(View.VISIBLE);
                tvName.setText(User.getPokemons().get(5).getName());
                Glide.with(TeamActivity.this)
                        .load(User.getPokeball(User.getPokemons().get(5).getPokeball())).apply(new RequestOptions().override(600, 200))
                        .into(ivPokeballSelected);

                Glide.with(TeamActivity.this)
                        .load(User.getPokemons().get(5).getImg().get(0)).apply(new RequestOptions().override(600, 200))
                        .into(ivPokemonSelected);
                btnRelease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (User.getPokemons().size() == 1){
                            Toast.makeText(TeamActivity.this,"You must have 1 pokemon in your team.",Toast.LENGTH_SHORT).show();
                        }else{

                            Toast.makeText(TeamActivity.this,"Deleted succesfully.",Toast.LENGTH_SHORT).show();
                            User.getPokemons().remove(5);
                            try {
                                User.saveUserData(TeamActivity.this,TeamActivity.this.openFileInput("persistence.json"));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            ivPokeballSelected.setVisibility(View.INVISIBLE);
                            ivPokemonSelected.setVisibility(View.INVISIBLE);
                            tvName.setVisibility(View.INVISIBLE);
                            btnRelease.setVisibility(View.INVISIBLE);

                            ivPokeball6.setVisibility(View.INVISIBLE);
                            ivPokemon6.setVisibility(View.INVISIBLE);
                            tvPokemon6.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

    }
}