package com.commit451.endlessrecyclerviewonscrolllistener.sample;

import com.google.gson.annotations.SerializedName;

public class Author {
    @SerializedName("login")
    String mLogin;
    @SerializedName("avatar_url")
    String mAvatarUrl;

    public String getLogin() {
        return mLogin;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }
}
