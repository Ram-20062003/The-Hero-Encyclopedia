package com.example.navigation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Biography {
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("alterEgos")
    private String alterEgos;
    @SerializedName("aliases")
    private List<String> aliases;
    @SerializedName("placeOfBirth")
    private String placeOfBirth;
    @SerializedName("firstAppearance")
    private String firstAppearance;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("alignment")
    private String alignment;

    public String getFullName() {
        return fullName;
    }

    public String getAlterEgos() {
        return alterEgos;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAlignment() {
        return alignment;
    }
}
