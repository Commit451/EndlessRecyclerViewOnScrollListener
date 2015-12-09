package com.commit451.endlessrecyclerviewonscrolllistener.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.commit451.endlessrecyclerviewonscrolllistener.EndlessRecyclerViewOnScrollListener;

import java.util.ArrayList;

public class OfflineActivity extends Activity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mGistRecyclerView;
    private CheeseAdapter mCheeseAdapter;

    private EndlessRecyclerViewOnScrollListener mEndlessRecyclerViewOnScrollListener = new EndlessRecyclerViewOnScrollListener() {
        @Override
        public void onLoadMore() {
            Log.d("TESTING", "onLoadMore");
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
                mCheeseAdapter.clear();
                mEndlessRecyclerViewOnScrollListener.reset();
                load();
            }
        });
        mCheeseAdapter = new CheeseAdapter();
        mGistRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mGistRecyclerView.setLayoutManager(gridLayoutManager);
        mGistRecyclerView.setAdapter(mCheeseAdapter);
        mEndlessRecyclerViewOnScrollListener.setLinearLayoutManager(gridLayoutManager);
        mGistRecyclerView.addOnScrollListener(mEndlessRecyclerViewOnScrollListener);
        load();
    }

    private void load() {
        mCheeseAdapter.setLoading(true);
        ArrayList<Cheese> cheeses = new ArrayList<>();
        for (int i=0; i<5; i++) {
            cheeses.add(Cheese.getRandomCheese());
        }
        mSwipeRefreshLayout.setRefreshing(false);
        mCheeseAdapter.setLoading(false);
        mCheeseAdapter.addAll(cheeses);
    }
}
