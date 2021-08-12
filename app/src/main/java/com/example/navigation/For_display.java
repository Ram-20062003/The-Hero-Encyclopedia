package com.example.navigation;

import java.util.List;

public class For_display {
    private String slug,gender,race,eye_color,hair_color,fullname,alteregos,pob,first_app,publisher,align,occupation,base,group_aff,rel;
    private List<String> aliases,height,weight;
    private Integer intelligence,strength,speed,durablity,power,combat;

    public For_display(String slug, String gender, String race, String eye_color, String hair_color, String fullname, String alteregos, String pob, String first_app, String publisher, String align, String occupation, String base, String group_aff, String rel, List<String> aliases, List<String> height, List<String> weight, Integer intelligence, Integer strength, Integer speed, Integer durablity, Integer power, Integer combat) {
        this.slug = slug;
        this.gender = gender;
        this.race = race;
        this.eye_color = eye_color;
        this.hair_color = hair_color;
        this.fullname = fullname;
        this.alteregos = alteregos;
        this.pob = pob;
        this.first_app = first_app;
        this.publisher = publisher;
        this.align = align;
        this.occupation = occupation;
        this.base = base;
        this.group_aff = group_aff;
        this.rel = rel;
        this.aliases = aliases;
        this.height = height;
        this.weight = weight;
        this.intelligence = intelligence;
        this.strength = strength;
        this.speed = speed;
        this.durablity = durablity;
        this.power = power;
        this.combat = combat;
    }

    public String getSlug() {
        return slug;
    }

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public String getEye_color() {
        return eye_color;
    }

    public String getHair_color() {
        return hair_color;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAlteregos() {
        return alteregos;
    }

    public String getPob() {
        return pob;
    }

    public String getFirst_app() {
        return first_app;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAlign() {
        return align;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getBase() {
        return base;
    }

    public String getGroup_aff() {
        return group_aff;
    }

    public String getRel() {
        return rel;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public List<String> getHeight() {
        return height;
    }

    public List<String> getWeight() {
        return weight;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public Integer getStrength() {
        return strength;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getDurablity() {
        return durablity;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getCombat() {
        return combat;
    }
}
