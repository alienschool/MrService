package com.example.news.mrservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private  static final String BaseUri="http://amtech.com.pk/autodocs/API/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit= new Retrofit.Builder().baseUrl(BaseUri).
                    addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
