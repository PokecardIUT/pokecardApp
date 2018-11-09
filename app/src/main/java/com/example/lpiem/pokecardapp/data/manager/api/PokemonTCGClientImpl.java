/*package com.example.lpiem.pokecardapp.data.manager.api;

import com.example.lpiem.pokecardapp.data.model.Card;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonTCGClientImpl implements PokemonTCGClient{

    @Override
    public Call<List<Card>> getAllCard() {
        String API_BASE_URL = "https://pokemontcg.io/";

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        Card cardList = retrofit.create(Card.class);
        return cardList;
    }
}
*/