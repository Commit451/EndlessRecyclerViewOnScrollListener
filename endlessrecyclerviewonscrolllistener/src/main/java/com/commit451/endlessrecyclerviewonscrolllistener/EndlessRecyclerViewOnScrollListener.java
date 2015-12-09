package com.commit451.endlessrecyclerviewonscrolllistener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Endless scrolling listener, used to provide functionality of loading more data as a user
 * approaches the end of scrolling through a list
 */
public abstract class EndlessRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {

    private static final int DEFAULT_VISIBLE_THRESHOLD = 5;

    // The total number of items in the data set after the last load
    private int mPreviousTotal = 0;
    // True if we are still waiting for the last set of data to load.
    private boolean mLoading = true;
    // The minimum amount of items to have below your current scroll position before mLoading more.
    private int mVisibleThreshold = DEFAULT_VISIBLE_THRESHOLD;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerViewOnScrollListener() {
        //You better set the layout manager later!
    }

    public EndlessRecyclerViewOnScrollListener(LinearLayoutManager linearLayoutManager) {
        mLinearLayoutManager = linearLayoutManager;
    }

    /**
     * Set the minimum amount of items to have below your current scroll position before loading more.
     * Default value is {@value  #DEFAULT_VISIBLE_THRESHOLD}
     * @param visibleThreshold the number of items left before {@link #onLoadMore()} is called
     */
    public void setVisibleThreshold(int visibleThreshold) {
        mVisibleThreshold = visibleThreshold;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        mLinearLayoutManager = linearLayoutManager;
    }

    /**
     * Call reset when the adapter is cleared
     */
    public void reset() {
        mLoading = false;
        mPreviousTotal = 0;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mLinearLayoutManager == null) {
            throw new IllegalStateException("LinearLayoutManager cannot be null. Set the LinearLayoutManager before the user is allowed to scroll!");
        }

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false;
                mPreviousTotal = totalItemCount;
            }
        }
        if (!mLoading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItemPosition + mVisibleThreshold)) {
            // End has been reached
            onLoadMore();

            mLoading = true;
        }
    }

    public abstract void onLoadMore();
}