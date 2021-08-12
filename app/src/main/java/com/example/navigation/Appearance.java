package com.example.navigation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Appearance {
    @SerializedName("gender")
    private String gender;
    @SerializedName("race")
    private String race;
    @SerializedName("height")
    private List<String> height;
    @SerializedName("weight")
    private List<String> weight;
    @SerializedName("eyeColor")
    private String eyeColor;
    @SerializedName("hairColor")
    private String hairColor;

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public List<String> getHeight() {
        return height;
    }

    public List<String> getWeight() {
        return weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }
}
