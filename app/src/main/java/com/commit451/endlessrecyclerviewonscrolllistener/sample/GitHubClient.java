package com.commit451.endlessrecyclerviewonscrolllistener.sample;


import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

public class GitHubClient {

    public static final String API_URL = "https://api.github.com";

    public interface GitHub {
        @GET("/gists/public")
        Call<List<Gist>> gists(@Query("page") int page);
    }

    private static GitHub mGithub;
    public static GitHub instance() {
        if (mGithub == null) {
            Retrofit restAdapter = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(API_URL)
                    .build();
            mGithub = restAdapter.create(GitHub.class);
        }
        return mGithub;
    }
}