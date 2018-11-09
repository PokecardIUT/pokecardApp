package com.example.lpiem.pokecardapp.data.manager.api;

import com.example.lpiem.pokecardapp.data.model.Github;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GithubServiceImpl {
    private GithubService githubService;

    public GithubServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        githubService = retrofit.create(GithubService.class);
    }

    public Observable<String> getGithubUsername(String id) {
        return githubService.getGitHubUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(user -> "Username: "+user.getName());
    }

    public Observable<String> getAllGithubUsername() {
        return githubService.getGitHubUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(users -> { String s = "Users:\n";
                    for (Github user : users) {
                       s+=(user.getName()+'\n');
                }

                return s;});
    }
}
