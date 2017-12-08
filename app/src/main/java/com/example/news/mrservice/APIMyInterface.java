package com.example.news.mrservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface APIMyInterface {
    @FormUrlEncoded
    @POST("testing-service.php")
    Call<String> testingPhp(@Field("test") String test);
}
