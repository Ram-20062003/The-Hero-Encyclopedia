package com.example.navigation;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="table_hero")
public class Hero_info_table {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "Id")
    private Integer id;

    @ColumnInfo(name = "hero_name")
    private String hero_name;

    @ColumnInfo(name="image")
    private String image_url;

    public Hero_info_table(Integer id, String hero_name, String image_url) {
        this.id = id;
        this.hero_name = hero_name;
        this.image_url = image_url;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHero_name() {
        return hero_name;
    }

    public void setHero_name(String hero_name) {
        this.hero_name = hero_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "Hero_info_table{" +
                "uid=" + uid +
                ", id=" + id +
                ", hero_name='" + hero_name + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
