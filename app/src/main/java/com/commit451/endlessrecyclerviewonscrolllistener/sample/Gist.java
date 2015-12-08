package com.commit451.endlessrecyclerviewonscrolllistener.sample;

import com.google.gson.annotations.SerializedName;

/**
 * A GitHub gist
 */
public class Gist {

    @SerializedName("description")
    String mDescription;
    @SerializedName("owner")
    Author mOwner;

    public String getDescription() {
        return mDescription;
    }

    public Author getOwner() {
        return mOwner;
    }
}
