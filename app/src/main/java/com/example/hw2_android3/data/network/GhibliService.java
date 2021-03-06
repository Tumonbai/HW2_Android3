package com.example.hw2_android3.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GhibliService {

    private GhibliService() { }

    private static GhibliApi ghibliApi;

    public static GhibliApi getInstance() {
        if (ghibliApi == null) {
            ghibliApi = buildRetrofit();
        }
        return ghibliApi;
    }

    private static GhibliApi buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://ghibliapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GhibliApi.class);
    }
}
