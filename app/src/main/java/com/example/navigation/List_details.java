package com.example.navigation;

public class List_details {
String name_list,image;
Integer id;

    public List_details(String name_list, String image, Integer id) {
        this.name_list = name_list;
        this.image = image;
        this.id = id;
    }

    public String getName_list() {
        return name_list;
    }

    public String getImage() {
        return image;
    }

    public Integer getId() {
        return id;
    }
}
