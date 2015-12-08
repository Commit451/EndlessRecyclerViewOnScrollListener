package com.commit451.endlessrecyclerviewonscrolllistener.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.commit451.endlessrecyclerviewonscrolllistener.EndlessRecyclerViewOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mGistRecyclerView;
    private GistAdapter mGistAdapter;
    private int mPage = 0;

    private final Callback<List<Gist>> mCallback = new Callback<List<Gist>>() {
        @Override
        public void onResponse(Response<List<Gist>> response, Retrofit retrofit) {
            if (!response.isSuccess()) {
                Toast.makeText(MainActivity.this, "Response error", Toast.LENGTH_SHORT).show();
                return;
            }
            mGistAdapter.setLoading(false);
            ArrayList<Gist> validGists = new ArrayList<>();
            for (Gist gist : response.body()) {
                if (gist.getOwner() != null) {
                    validGists.add(gist);
                }
            }
            mGistAdapter.addAll(validGists);
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGistAdapter = new GistAdapter();
        mGistRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mGistRecyclerView.setAdapter(mGistAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mGistRecyclerView.setLayoutManager(linearLayoutManager);
        mGistRecyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                mPage++;
                load();
            }
        });

        load();
    }

    private void load() {
        mGistAdapter.setLoading(true);
        GitHubClient.instance().gists(mPage).enqueue(mCallback);
    }
}
