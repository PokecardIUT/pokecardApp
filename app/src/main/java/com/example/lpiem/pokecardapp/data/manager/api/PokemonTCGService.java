package com.example.lpiem.pokecardapp.data.manager.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PokemonTCGService {
    private PokemonTCGClient pokemonTCGClient;

    public PokemonTCGService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokemonTCGClient = retrofit.create(PokemonTCGClient.class);
    }

    public Observable<String> getRxCardName(String id) {
        return pokemonTCGClient.getCard(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(card -> "Card: "+card.getName());
    }
}
