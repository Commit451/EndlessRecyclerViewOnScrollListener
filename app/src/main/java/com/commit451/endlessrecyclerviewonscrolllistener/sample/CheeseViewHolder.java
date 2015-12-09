package com.commit451.endlessrecyclerviewonscrolllistener.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CheeseViewHolder extends RecyclerView.ViewHolder {

    public static CheeseViewHolder newInstance(ViewGroup parent) {
        return new CheeseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gist, parent, false));
    }

    private TextView author;
    private ImageView authorImage;


    public CheeseViewHolder(View view) {
        super(view);
        author = (TextView) view.findViewById(R.id.gist_author);
        authorImage = (ImageView) view.findViewById(R.id.gist_author_image);
    }

    public void bind(Cheese cheese) {
        author.setText(cheese.getName());
        authorImage.setImageResource(cheese.getDrawable());
    }
}
