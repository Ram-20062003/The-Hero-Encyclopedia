package com.example.navigation;

import com.google.gson.annotations.SerializedName;

public class SuperHero {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("slug")
    private String slug;
    @SerializedName("powerstats")
    private Powerstats powerstats;
    @SerializedName("appearance")
    private Appearance appearance;
    @SerializedName("biography")
    private Biography biography;
    @SerializedName("work")
    private Work work;
    @SerializedName("connections")
    private Connections connections;
    @SerializedName("images")
    private Images images;

    public Powerstats getPowerstats() {
        return powerstats;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public Biography getBiography() {
        return biography;
    }

    public Work getWork() {
        return work;
    }

    public Connections getConnections() {
        return connections;
    }

    public Images getImages() {
        return images;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
