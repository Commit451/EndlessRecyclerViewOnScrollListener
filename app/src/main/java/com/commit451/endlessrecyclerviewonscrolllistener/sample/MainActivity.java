package com.commit451.endlessrecyclerviewonscrolllistener.sample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.commit451.endlessrecyclerviewonscrolllistener.EndlessRecyclerViewOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mGistRecyclerView;
    private GistAdapter mGistAdapter;
    private int mPage = 0;

    private final Callback<List<Gist>> mCallback = new Callback<List<Gist>>() {
        @Override
        public void onResponse(Response<List<Gist>> response, Retrofit retrofit) {
            mSwipeRefreshLayout.setRefreshing(false);
            mGistAdapter.setLoading(false);
            if (!response.isSuccess()) {
                Toast.makeText(MainActivity.this, "Response error " + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                return;
            }
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
            mSwipeRefreshLayout.setRefreshing(false);
            mGistAdapter.setLoading(false);
            Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    private final EndlessRecyclerViewOnScrollListener mEndlessRecyclerViewOnScrollListener = new EndlessRecyclerViewOnScrollListener() {
        @Override
        public void onLoadMore() {
            mPage++;
            Log.d("TESTING", "onLoadMore page" + mPage);
            load();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mGistAdapter.clear();
                mEndlessRecyclerViewOnScrollListener.reset();
                load();
            }
        });
        mGistAdapter = new GistAdapter();
        mGistRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mGistRecyclerView.setAdapter(mGistAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mGistRecyclerView.setLayoutManager(linearLayoutManager);
        mEndlessRecyclerViewOnScrollListener.setLinearLayoutManager(linearLayoutManager);
        mGistRecyclerView.addOnScrollListener(mEndlessRecyclerViewOnScrollListener);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            }
        });
        load();
    }

    private void load() {
        mGistAdapter.setLoading(true);
        GitHubClient.instance().gists(mPage).enqueue(mCallback);
    }
}
