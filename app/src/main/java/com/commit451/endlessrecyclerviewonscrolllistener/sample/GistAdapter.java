package com.commit451.endlessrecyclerviewonscrolllistener.sample;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;

public class GistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private static final int FOOTER_COUNT = 1;

    private ArrayList<Gist> mGists;
    private boolean mLoading;

    public GistAdapter() {
        mGists = new ArrayList<>();
    }

    public void addAll(Collection<Gist> gists) {
        mGists.addAll(gists);
        notifyDataSetChanged();
    }

    public void setLoading(boolean loading) {
        mLoading = loading;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM:
                return GistViewHolder.newInstance(parent);
            case TYPE_FOOTER:
                return LoadingViewHolder.newInstance(parent);
        }
        throw new IllegalStateException("No holder for type " + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GistViewHolder) {
            ((GistViewHolder) holder).bind(mGists.get(position));
        } else if (holder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder).bind(mLoading);
        }

    }

    @Override
    public int getItemCount() {
        return mGists.size() + FOOTER_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mGists.size()) {
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }
}