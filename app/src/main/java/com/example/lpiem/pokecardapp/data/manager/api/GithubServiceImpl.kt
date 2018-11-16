package com.example.lpiem.pokecardapp.data.manager.api


import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GithubServiceImpl {
    private val githubService: GithubService

    val allGithubUsername: Observable<String>
        get() = githubService.gitHubUsers
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { users ->
                    var s = "Users:\n"
                    for (user in users) {
                        s += user.name + '\n'
                    }

                    s
                }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        githubService = retrofit.create(GithubService::class.java)
    }

    fun getGithubUsername(id: String): Observable<String> {
        return githubService.getGitHubUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { user -> "Username: " + user.name }
    }
}
