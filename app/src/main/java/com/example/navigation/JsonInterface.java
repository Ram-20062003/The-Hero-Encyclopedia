package com.example.navigation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonInterface {
    @GET("all.json")
    Call<List<SuperHero>> getSuperHero();
}
