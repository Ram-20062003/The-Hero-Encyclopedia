package com.example.navigation;

import com.google.gson.annotations.SerializedName;

public class Powerstats {
    @SerializedName(("intelligence"))
    private Integer intelligence;
    @SerializedName("strength")
    private Integer strength;
    @SerializedName("speed")
    private Integer speed;
    @SerializedName("durability")
    private Integer durability;
    @SerializedName("power")
    private Integer power;
    @SerializedName("combat")
    private Integer combat;

    public Integer getIntelligence() {
        return intelligence;
    }

    public Integer getStrength() {
        return strength;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getDurability() {
        return durability;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getCombat() {
        return combat;
    }
}
