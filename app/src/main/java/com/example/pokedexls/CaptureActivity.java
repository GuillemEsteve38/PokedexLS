package com.example.pokedexls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CaptureActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private PokeballAdapter pokeballAdapter;
    private int pokemonid;
    private boolean captured;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Capture");
        viewPager = findViewById(R.id.viewPager);
        pokemonid = getIntent().getIntExtra("id",1);

        pokeballAdapter = new PokeballAdapter(User.getPokeballs());
        viewPager.setAdapter(pokeballAdapter);


    }
    public class PokeballAdapter extends RecyclerView.Adapter<PokeballViewHolder> {

        private List<Pokeball> pokeballList;

        public PokeballAdapter(List<Pokeball> pokeballList) {
            this.pokeballList = pokeballList;
        }

        @NonNull
        @Override
        public PokeballViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokeball_item, parent, false);
            return new PokeballViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PokeballViewHolder holder, int position) {

            Pokeball pokeball = pokeballList.get(position);
            Glide.with(CaptureActivity.this)
                    .load(pokeball.getImageResource())
                    .into( holder.pokeballImage);
            holder.pokeballQuantity.setText("X" + String.valueOf(pokeball.getQuantity()));
            holder.pokeballStats.setText(probabilities(pokeball));
            for (Pokemon p: User.getPokemons()) {
                if (p.getName().equals(PokemonLab.getsCrimeLab().getChat(pokemonid).getName()) || User.getPokemons().size() == 6){
                    holder.capture.setEnabled(false);
                }
            }
            holder.capture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Random rnd = new Random();
                    int prob = 0;
                    float res = 0;
                    int money =0;
                    int perc = rnd.nextInt(101) + 1;
                    holder.pokeballQuantity.setText("X" + String.valueOf(pokeball.getQuantity()));

                        switch (PokemonLab.getsCrimeLab().getChat(pokemonid).getEvolution()){
                            case 1:
                                rnd = new Random();
                                prob = rnd.nextInt(61) + 20;
                                res = (float) (600 - prob) /600*pokeball.getMultiplier();
                                money = 400 + (100*prob);
                                if (pokeball.getType().equals("Masterball")){
                                    perc = 1;
                                    res = 1;
                                }

                                break;
                            case 2:
                                rnd = new Random();
                                prob = rnd.nextInt(201) + 80;
                                res = (float) (600 - prob) /600*pokeball.getMultiplier();
                                money = 400 + (100*prob);
                                if (pokeball.getType().equals("Masterball")){
                                    perc = 1;
                                    res = 1;
                                }

                                break;
                            case 3:
                                rnd = new Random();
                                prob = rnd.nextInt(351) + 200;
                                res = (float) (600 - prob) /600*pokeball.getMultiplier();
                                money = 400 + (100*prob);
                                if (pokeball.getType().equals("Masterball")){
                                    perc = 1;
                                    res = 1;
                                }

                                break;
                            case 4:
                                rnd = new Random();
                                prob = rnd.nextInt(501) + 350;
                                res = (float) (600 - prob) /600*pokeball.getMultiplier();
                                money = 400 + (100*prob);
                                if (pokeball.getType().equals("Masterball")){
                                    perc = 1;
                                    res = 1;
                                }

                                break;
                        }


                        if (pokeball.getQuantity() > 0) {
                            if (perc <= res * 100) {
                                try {
                                    User.setMoney(User.getMoney() + money);
                                    PokemonLab.getsCrimeLab().getChat(pokemonid).setPokeball(pokeball.getType());
                                    User.getPokemons().add(PokemonLab.getsCrimeLab().getChat(pokemonid));
                                    User.saveUserData(CaptureActivity.this, CaptureActivity.this.openFileInput("persistence.json"));
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                                Toast.makeText(CaptureActivity.this, "Captured", Toast.LENGTH_SHORT).show();
                                Toast.makeText(CaptureActivity.this, "You earned " + money + "$", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CaptureActivity.this, MainActivity.class);
                                PokemonLab.clearList();
                                startActivity(intent);
                            } else {
                                Toast.makeText(CaptureActivity.this, "Missed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(CaptureActivity.this, "You not have any pokeball of this type", Toast.LENGTH_SHORT).show();
                        }


                }
            });
        }

        @Override
        public int getItemCount() {
            return pokeballList.size();
        }


    }
    private class PokeballViewHolder extends RecyclerView.ViewHolder {
        ImageView pokeballImage;
        TextView pokeballQuantity;
        TextView pokeballStats;
        Button capture;

        public PokeballViewHolder(@NonNull View itemView) {
            super(itemView);
            pokeballImage = itemView.findViewById(R.id.pokeballImage);
            pokeballQuantity = itemView.findViewById(R.id.pokeballQuantity);
            pokeballStats = itemView.findViewById(R.id.pokeballStats);
            capture = itemView.findViewById(R.id.startCaptureButton);

        }
    }

    private String probabilities(Pokeball pokeball){

        if (pokeball.getType().equals("Masterball")){
            String one, two, three, four;
            one = "100% - 100%";
            two = "100% - 100%";
            three = "100% - 100%";
            four = "100% - 100%";
            return one +"\n"+two+"\n"+three+"\n"+four;
        }

        Random rnd = new Random();
        int prob = 0;
        float res1 =  (float) (600 - 20) /600*pokeball.getMultiplier();
        float res2 =  (float) (600 - 80) /600*pokeball.getMultiplier();
        if (res1 > 1){
            res1 = 1;
        }
        if (res2 > 1){
            res2 = 1;
        }
        String first = (int)(res1*100)+"% - "+ String.valueOf((int)(res2*100))+"%";
        float res3 =  (float) (600 - 80) /600*pokeball.getMultiplier();
        float res4 =  (float) (600 - 200) /600*pokeball.getMultiplier();
        if (res3 > 1){
            res3 = 1;
        }
        if (res4 > 1){
            res4 = 1;
        }
        String sec = String.valueOf((int)(res3*100))+"% - "+ String.valueOf((int)(res4*100))+"%";
        float res5 =  (float) (600 - 200) /600*pokeball.getMultiplier();
        float res6 =  (float) (600 - 350) /600*pokeball.getMultiplier();
        if (res5 > 1){
            res5 = 1;
        }
        if (res6 > 1){
            res6 = 1;
        }
        String third = String.valueOf((int)(res5*100))+"% - "+ String.valueOf((int)(res6*100))+"%";
        float res7 =  (float) (600 - 350) /600*pokeball.getMultiplier();
        float res8 =  (float) (600 - 500) /600*pokeball.getMultiplier();
        if (res7 > 1){
            res7 = 1;
        }
        if (res8 > 1){
            res8 = 1;
        }
        String fort = String.valueOf((int)(res7*100))+"% - "+ String.valueOf((int)(res8*100))+"%";

        return first +"\n"+sec+"\n"+third+"\n"+fort;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}