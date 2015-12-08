package com.commit451.endlessrecyclerviewonscrolllistener.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class GistViewHolder extends RecyclerView.ViewHolder {

    public static GistViewHolder newInstance(ViewGroup parent) {
        return new GistViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gist, parent, false));
    }

    private TextView author;
    private ImageView authorImage;


    public GistViewHolder(View view) {
        super(view);
        author = (TextView) view.findViewById(R.id.gist_author);
        authorImage = (ImageView) view.findViewById(R.id.gist_author_image);
    }

    public void bind(Gist gist) {
        author.setText(gist.getOwner().getLogin());
        Picasso.with(itemView.getContext())
                .load(gist.getOwner().getAvatarUrl())
                .into(authorImage);
    }
}