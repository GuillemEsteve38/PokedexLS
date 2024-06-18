package com.example.pokedexls;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Random;

public class PokemonDetailActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvType;
    private TextView tvAbility;
    private TextView tvDesc;
    private ImageView ivFront;
    private ImageView ivBack;
    private  ImageView ivtype1,ivtype2;
    private Button btnCapture;
    private ProgressBar pbar1,pbar2,pbar3,pbar4,pbar5;
    private TextView tvStat1,tvStat2,tvStat3,tvStat4,tvStat5;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pokemon Detail");
        int  a = getIntent().getIntExtra("id",1);
        tvName = findViewById(R.id.tvName);
        tvType = findViewById(R.id.tvType);
        tvDesc = findViewById(R.id.tvDesc);
        tvAbility = findViewById(R.id.tvAbility);
        ivFront = findViewById(R.id.ivFront);
        ivBack = findViewById(R.id.ivBack);
        btnCapture = findViewById(R.id.btnCapture);
        ivtype1 = findViewById(R.id.ivtype1);
        ivtype2 = findViewById(R.id.ivtype2);

        Random rnd = new Random();
        if (PokemonLab.getsCrimeLab().getChat(a).getAbilities().size() >= 2){
            int ran = rnd.nextInt(2);
            tvAbility.setText(PokemonLab.getsCrimeLab().getChat(a).getAbilities().get(ran).getName());

        }else{
            tvAbility.setText(PokemonLab.getsCrimeLab().getChat(a).getAbilities().get(0).getName());

        }


        pbar1 = findViewById(R.id.pbar1);
        pbar2 = findViewById(R.id.pbar2);
        pbar3 = findViewById(R.id.pbar3);
        pbar4 = findViewById(R.id.pbar4);
        pbar5 = findViewById(R.id.pbar5);

        tvStat1 = findViewById(R.id.tvStat1);
        tvStat2 = findViewById(R.id.tvStat2);
        tvStat3 = findViewById(R.id.tvStat3);
        tvStat4 = findViewById(R.id.tvStat4);
        tvStat5 = findViewById(R.id.tvStat5);

        ivtype1.setImageResource(findType(PokemonLab.getsCrimeLab().getChat(a).getType().get(0)));
        if (PokemonLab.getsCrimeLab().getChat(a).getType().size() >1){
            ivtype2.setImageResource(findType(PokemonLab.getsCrimeLab().getChat(a).getType().get(1)));
        }else {
            ivtype2.setVisibility(View.INVISIBLE);
        }


        pbar1.setProgress(PokemonLab.getsCrimeLab().getChat(a).getStats().get(0).getStat(),true);
        tvStat1.setText(PokemonLab.getsCrimeLab().getChat(a).getStats().get(0).getName());

        pbar2.setProgress(PokemonLab.getsCrimeLab().getChat(a).getStats().get(1).getStat());
        tvStat2.setText(PokemonLab.getsCrimeLab().getChat(a).getStats().get(1).getName());

        pbar3.setProgress(PokemonLab.getsCrimeLab().getChat(a).getStats().get(2).getStat());
        tvStat3.setText(PokemonLab.getsCrimeLab().getChat(a).getStats().get(2).getName());

        pbar4.setProgress(PokemonLab.getsCrimeLab().getChat(a).getStats().get(3).getStat());
        tvStat4.setText(PokemonLab.getsCrimeLab().getChat(a).getStats().get(3).getName());

        pbar5.setProgress(PokemonLab.getsCrimeLab().getChat(a).getStats().get(4).getStat());
        tvStat5.setText(PokemonLab.getsCrimeLab().getChat(a).getStats().get(4).getName());


        tvName.setText(PokemonLab.getsCrimeLab().getChat(a).getName());
        //tvType.setText(PokemonLab.getsCrimeLab().getChat(a).getType().get(0));

        Glide.with(this)
                .load(PokemonLab.getsCrimeLab().getChat(a).getImg().get(0))
                .into(ivFront);
        Glide.with(this)
                .load(PokemonLab.getsCrimeLab().getChat(a).getImg().get(1))
                .into(ivBack);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PokemonDetailActivity.this, CaptureActivity.class);
                intent.putExtra("id", a);
                startActivity(intent);
            }
        });
        fetchData(a);
    }

    private void fetchData(int id) {

        PokemonLab.getsCrimeLab().getDesc(String.valueOf(id),new PokemonLab.DataCallback() {

            @Override
            public void onSuccess(List<Pokemon> dataList) {

            }

            @Override
            public void onSuccess(String data) {
                tvDesc.setText(data);
            }

            @Override
            public void onFailure(String errorMessage) {
                // Handle the error

                //Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });



    }

    private int findType(String p){
        int img = 0;
        switch (p){
            case "bug":
                img = R.drawable.bug;
                break;
            case "dark":
                img = R.drawable.dark;
                break;
            case  "dragon":
                img = R.drawable.dragon;
                break;
            case "electric":
                img = R.drawable.electric;
                break;
            case "fairy":
                img = R.drawable.fairy;
                break;
            case "fighting":
                img = R.drawable.fighting;
                break;
            case  "fire":
                img = R.drawable.fire;
                break;
            case "flying":
                img = R.drawable.flying;
                break;
            case "ghost":
                img = R.drawable.ghost;
                break;
            case "grass":
                img = R.drawable.grass;
                break;
            case  "ground":
                img = R.drawable.ground;
                break;
            case "ice":
                img = R.drawable.ice;
                break;
            case "normal":
                img = R.drawable.normal;
                break;
            case "poison":
                img = R.drawable.poison;
                break;
            case  "psychic":
                img = R.drawable.psychic;
                break;
            case "rock":
                img = R.drawable.rock;
                break;
            case  "steel":
                img = R.drawable.steel;
                break;
            case "water":
                img = R.drawable.water;
                break;

        }
        return img;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                PokemonLab.clearList();
                PokemonLab.getsCrimeLab().clearOffset();
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}