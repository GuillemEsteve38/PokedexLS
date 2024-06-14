package com.example.pokedexls.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pokedexls.PokemonDetailActivity;
import com.example.pokedexls.PokemonLab;
import com.example.pokedexls.R;
import com.example.pokedexls.TeamActivity;
import com.example.pokedexls.User;
import com.example.pokedexls.databinding.FragmentDashboardBinding;

import java.io.FileNotFoundException;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ImageView ivPlayer;
    private EditText editTextName;
    private Button btnName;
    private ImageView ivMoney;
    private TextView tvMoney;
    private TableLayout tableLayout;
    private ImageView imageView6, imageView7, imageView4, imageView5;
    private TextView textView4, textView5, textView2, textView3;
    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        ivPlayer = view.findViewById(R.id.ivPlayer);
        ivMoney = view.findViewById(R.id.ivMoney);
        imageView6 = view.findViewById(R.id.imageView6);
        imageView7 = view.findViewById(R.id.imageView7);
        imageView4 = view.findViewById(R.id.imageView4);
        imageView5 = view.findViewById(R.id.imageView5);
        Glide.with(getContext())
                .load(User.getPokeballs().get(0).getImageResource()).apply(new RequestOptions().override(600, 200))
                .into(imageView6);
        Glide.with(getContext())
                .load(User.getPokeballs().get(1).getImageResource()).apply(new RequestOptions().override(600, 200))
                .into(imageView7);
        Glide.with(getContext())
                .load(User.getPokeballs().get(2).getImageResource()).apply(new RequestOptions().override(600, 200))
                .into(imageView4);
        Glide.with(getContext())
                .load(User.getPokeballs().get(3).getImageResource()).apply(new RequestOptions().override(600, 200))
                .into(imageView5);
        editTextName = view.findViewById(R.id.editTextName);
        editTextName.setText(User.getName());
        editTextName.setEnabled(false);

        btnName = view.findViewById(R.id.btnName);
        button = view.findViewById(R.id.button);


        tvMoney = view.findViewById(R.id.tvMoney);
        tvMoney.setText(String.valueOf(User.getMoney()));
        textView4 = view.findViewById(R.id.textView4);
        textView5 = view.findViewById(R.id.textView5);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        textView4.setText(String.valueOf(User.getPokeballs().get(0).getQuantity()));
        textView5.setText(String.valueOf(User.getPokeballs().get(1).getQuantity()));
        textView2.setText(String.valueOf(User.getPokeballs().get(2).getQuantity()));
        textView3.setText(String.valueOf(User.getPokeballs().get(3).getQuantity()));

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.isEnabled()){
                    btnName.setText("CHANGE");
                    User.setName(String.valueOf(editTextName.getText()));
                    try {
                        User.saveUserData(getContext(),getContext().openFileInput("persistence.json"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    editTextName.setEnabled(false);
                }else{
                    btnName.setText("ENTER");

                    editTextName.setEnabled(true);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TeamActivity.class);
                //intent.putExtra("id", mCrime.getId());
                startActivity(intent);
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