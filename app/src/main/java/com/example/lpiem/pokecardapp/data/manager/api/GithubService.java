package com.example.lpiem.pokecardapp.data.manager.api;

import com.example.lpiem.pokecardapp.data.model.Github;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubService {
    @GET("users/{username}")
    Observable<Github> getGitHubUser(@Path("username") String userName);
    @GET("users")
    Observable<List<Github>> getGitHubUsers();
}
