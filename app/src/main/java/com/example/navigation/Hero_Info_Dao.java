package com.example.navigation;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Hero_Info_Dao {
    @Insert
    void insert_hero(Hero_info_table hero_info_table);

    @Query("SELECT*FROM table_hero")
    List<Hero_info_table> get_hero();

    @Query("SELECT*FROM table_hero WHERE hero_name LIKE :name ")
    Hero_info_table findByName(String name);

    @Delete
    void delete_hero(Hero_info_table hero_info_table);
}
