package com.example.pokedexls.ui.home;



import static androidx.navigation.Navigation.findNavController;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pokedexls.ApiResponseCallback;
import com.example.pokedexls.MainActivity;
import com.example.pokedexls.Pokemon;
import com.example.pokedexls.PokemonDetailActivity;
import com.example.pokedexls.PokemonLab;
import com.example.pokedexls.R;
import com.example.pokedexls.Stats;
import com.example.pokedexls.User;
import com.example.pokedexls.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    EditText tb_pokemon;
    ImageButton btn;
    TextView tvName;
    TextView tvType;
     ImageView iv_pokemon;
     Button btnSerch;
     EditText tvSearch;
    private RecyclerView mRecyclerView;

    private PokemonAdapter mAdapter;
    private List<Pokemon> dataList = new ArrayList<>();
    private PokemonLab dataManager;
    private boolean requesting = false;
    private int offset = 0;
    private  FragmentHomeBinding binding;
    private  View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnSerch = view.findViewById(R.id.btnSearch);
        tvSearch = view.findViewById(R.id.tvSearch);

        dataManager = PokemonLab.get(getContext());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new PokemonAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null && dataList.size() >=15 && layoutManager.findLastCompletelyVisibleItemPosition() == dataList.size() - 1) {

                    PokemonLab.getsCrimeLab().setOffset(15);
                    fetchData(PokemonLab.getsCrimeLab().getOffset());
                }
            }
        });
        btnSerch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requesting = true;
                //PokemonLab.clearList();
                //PokemonLab.getsCrimeLab().clearOffset();
                if (String.valueOf(tvSearch.getText()).isEmpty()){
                    requesting = false;
                }
                fetchData(PokemonLab.getsCrimeLab().getOffset());
            }
        });
        requesting = false;
        fetchData(PokemonLab.getsCrimeLab().getOffset());
        return view;
    }

    private void fetchData(int offset) {
        if (!requesting){
            dataManager.pokemonList(new PokemonLab.DataCallback() {

                @Override
                public void onSuccess(List<Pokemon> data) {
                    requesting = false;
                    dataList.clear();
                    dataList.addAll(data);
                    mAdapter.notifyDataSetChanged();
                    mAdapter.updateAdapter(dataList);
                }

                @Override
                public void onSuccess(String dataList) {

                }

                @Override
                public void onFailure(String errorMessage) {
                    // Handle the error
                    requesting = false;
                    //Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            },offset);

        }else{
            requesting = false;
            dataManager.getpokemon(new PokemonLab.DataCallback() {

                @Override
                public void onSuccess(List<Pokemon> data) {
                    requesting = false;
                    dataList.clear();
                    dataList.add(findPokemonByName(data, String.valueOf(tvSearch.getText())));
                    mAdapter.notifyDataSetChanged();
                    mAdapter.updateAdapter(dataList);
                }

                @Override
                public void onSuccess(String dataList) {

                }

                @Override
                public void onFailure(String errorMessage) {
                    // Handle the error
                    requesting = false;
                    //Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            },"https://pokeapi.co/api/v2/pokemon/"+tvSearch.getText());
        }

    }
    public static Pokemon findPokemonByName(List<Pokemon> people, String name) {
        for (Pokemon person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public class PokemonAdapter extends RecyclerView.Adapter<ChatHolder>{
        private List<Pokemon> mList;

        public PokemonAdapter(List<Pokemon> mCrime) {
            mList = mCrime;
        }

        public void setmCrimes(List<Pokemon> mCrime) {
            mList = mCrime;
        }
        public void updateAdapter(List<Pokemon> mDataList) {
            this.mList = mDataList;
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            return new ChatHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
            Pokemon crime = mList.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }


    private class ChatHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Pokemon mCrime;

        private TextView mTitle;
        private TextView mName;
        private ImageView iv_pokemon;
        private ImageView iv_pokeball;
        private TextView mDate;

        public ChatHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.box_pokemon,parent,false));
            itemView.setOnClickListener(this);

            mName = (TextView) itemView.findViewById(R.id.pk_name);
            mTitle = (TextView) itemView.findViewById(R.id.pk_number);
            iv_pokemon = (ImageView) itemView.findViewById(R.id.iv_pokemon);
            iv_pokeball = (ImageView) itemView.findViewById(R.id.iv_pokeball);
        }

        public void bind(Pokemon crime){
            mCrime = crime;
            String s1 = mCrime.getName().substring(0, 1).toUpperCase() + mCrime.getName().substring(1).toLowerCase();
            mName.setText(s1);
            mTitle.setText(mCrime.getType().get(0));
            iv_pokeball.setImageResource(R.drawable.gray);
            for (Pokemon p: User.getPokemons()) {
                if (p.getName().equals(mCrime.getName())){
                    Glide.with(itemView.getContext())
                            .load(User.getPokeball(p.getPokeball()))
                            .into(iv_pokeball);

                }
            }
            Glide.with(itemView.getContext())
                    .load(mCrime.getImg().get(0)).apply(new RequestOptions().override(600, 200))
                    .into(iv_pokemon);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), PokemonDetailActivity.class);
            intent.putExtra("id", mCrime.getId());
            startActivity(intent);
            //findNavController(v).navigate(R.id.listtodetail);
        }
    }
}