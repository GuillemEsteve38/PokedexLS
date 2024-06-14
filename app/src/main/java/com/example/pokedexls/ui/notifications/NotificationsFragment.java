package com.example.pokedexls.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pokedexls.PokemonLab;
import com.example.pokedexls.R;
import com.example.pokedexls.User;
import com.example.pokedexls.databinding.FragmentNotificationsBinding;

import java.io.FileNotFoundException;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private LinearLayout lyPokeballN;
    private LinearLayout lySuperball;
    private LinearLayout lyUltraball;
    private LinearLayout lyMasterball;
    private ImageView pokeballImage;
    private ImageView superballImage;
    private ImageView ultraballImage;
    private ImageView masterballImage;
    private ImageView pokeballDollar;
    private ImageView superballDollar;
    private ImageView ultraballDollar;
    private ImageView masterballDollar;
    private TextView pokeballPrice;
    private TextView superballPrice;
    private TextView ultraballPrice;
    private TextView masterballPrice;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Initialize the LinearLayouts
        lyPokeballN = view.findViewById(R.id.ly_pokeballN);
        lySuperball = view.findViewById(R.id.ly_superball);
        lyUltraball = view.findViewById(R.id.ly_ultraball);
        lyMasterball = view.findViewById(R.id.ly_masterball);
        pokeballImage = view.findViewById(R.id.pokeballImage);
        superballImage = view.findViewById(R.id.superballImage);
        ultraballImage = view.findViewById(R.id.ultraballImage);
        masterballImage = view.findViewById(R.id.masterballImage);


        Glide.with(getContext())
                .load(User.getPokeballs().get(0).getImageResource()).apply(new RequestOptions().override(600, 200))
                .into(pokeballImage);
        Glide.with(getContext())
                .load(User.getPokeballs().get(1).getImageResource()).apply(new RequestOptions().override(600, 200))
                .into(superballImage);
        Glide.with(getContext())
                .load(User.getPokeballs().get(2).getImageResource()).apply(new RequestOptions().override(600, 200))
                .into(ultraballImage);
        Glide.with(getContext())
                .load(User.getPokeballs().get(3).getImageResource()).apply(new RequestOptions().override(600, 200))
                .into(masterballImage);
        lyPokeballN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User.getMoney() >= 100){
                    User.setMoney(User.getMoney()-100);
                    Toast.makeText(getContext(),"You bought 1 Pokeball.",Toast.LENGTH_SHORT).show();
                    User.getPokeballs().get(0).setQuantity(User.getPokeballs().get(0).getQuantity()+1);
                    try {
                        User.saveUserData(getContext(),getContext().openFileInput("persistence.json"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        lySuperball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User.getMoney() >= 500){
                    User.setMoney(User.getMoney()-500);
                    Toast.makeText(getContext(),"You bought 1 Superball.",Toast.LENGTH_SHORT).show();
                    User.getPokeballs().get(1).setQuantity(User.getPokeballs().get(1).getQuantity()+1);
                    try {
                        User.saveUserData(getContext(),getContext().openFileInput("persistence.json"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        lyUltraball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User.getMoney() >= 1500){
                    User.setMoney(User.getMoney()-1500);
                    Toast.makeText(getContext(),"You bought 1 Ultraball.",Toast.LENGTH_SHORT).show();
                    User.getPokeballs().get(2).setQuantity(User.getPokeballs().get(2).getQuantity()+1);
                    try {
                        User.saveUserData(getContext(),getContext().openFileInput("persistence.json"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        lyMasterball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User.getMoney() >= 100000){
                    User.setMoney(User.getMoney()-100000);
                    Toast.makeText(getContext(),"You bought 1 Masterball.",Toast.LENGTH_SHORT).show();
                    User.getPokeballs().get(3).setQuantity(User.getPokeballs().get(3).getQuantity()+1);
                    try {
                        User.saveUserData(getContext(),getContext().openFileInput("persistence.json"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PokemonLab.clearList();
        PokemonLab.getsCrimeLab().clearOffset();
        binding = null;
    }
}