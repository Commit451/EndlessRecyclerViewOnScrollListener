package com.commit451.endlessrecyclerviewonscrolllistener.sample;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;

public class CheeseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private static final int FOOTER_COUNT = 1;

    private ArrayList<Cheese> mValues;
    private boolean mLoading;

    public CheeseAdapter() {
        mValues = new ArrayList<>();
    }

    public void addAll(Collection<Cheese> gists) {
        mValues.addAll(gists);
        notifyDataSetChanged();
    }

    public void setLoading(boolean loading) {
        mLoading = loading;
        notifyDataSetChanged();
    }

    public void clear() {
        mValues.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM:
                return CheeseViewHolder.newInstance(parent);
            case TYPE_FOOTER:
                return LoadingViewHolder.newInstance(parent);
        }
        throw new IllegalStateException("No holder for type " + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CheeseViewHolder) {
            ((CheeseViewHolder) holder).bind(mValues.get(position));
        } else if (holder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder).bind(mLoading);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size() + FOOTER_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mValues.size()) {
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }
}
