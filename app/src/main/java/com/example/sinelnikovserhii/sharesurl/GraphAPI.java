package com.example.sinelnikovserhii.sharesurl;

import android.widget.ImageButton;

import com.google.gson.JsonObject;

import org.json.JSONObject;

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
 * Created by sinelnikovserhii on 11.07.17.
 */

 interface GraphAPI {
   @GET("/query")
   Call<JsonObject> getData(@QueryMap Map<String, String> queryMap);

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://www.alphavantage.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}