package com.example.navigation;

import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("xs")
    private String xs;
    @SerializedName("sm")
    private String sm;
    @SerializedName("md")
    private String md;
    @SerializedName("lg")
    private String lg;

    public String getXs() {
        return xs;
    }

    public String getSm() {
        return sm;
    }

    public String getMd() {
        return md;
    }

    public String getLg() {
        return lg;
    }
}
