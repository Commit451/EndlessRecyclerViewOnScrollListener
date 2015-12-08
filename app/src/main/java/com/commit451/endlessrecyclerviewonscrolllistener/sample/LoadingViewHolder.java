package com.commit451.endlessrecyclerviewonscrolllistener.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public static LoadingViewHolder newInstance(ViewGroup parent) {
        return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_loading, parent, false));
    }

    public LoadingViewHolder(View view) {
        super(view);
    }

    public void bind(boolean show) {
        itemView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}