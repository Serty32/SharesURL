package com.example.sinelnikovserhii.sharesurl;

import android.app.AlertDialog;

import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by sinelnikovserhii on 04.05.17.
 */

public interface SharesAPI {

    @GET("yql")
    Call<JsonObject> getData(@Query("q") String query, @QueryMap Map<String, String> queryMap);

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .client(httpClient)
            .baseUrl("http://query.yahooapis.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
