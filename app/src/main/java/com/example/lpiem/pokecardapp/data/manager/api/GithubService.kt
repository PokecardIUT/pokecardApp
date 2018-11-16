package com.example.lpiem.pokecardapp.data.manager.api

import com.example.lpiem.pokecardapp.data.model.Github

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface GithubService {
    @get:GET("users")
    val gitHubUsers: Observable<List<Github>>

    @GET("users/{username}")
    fun getGitHubUser(@Path("username") userName: String): Observable<Github>
}
