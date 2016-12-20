package com.example.d062654.faciliman.Connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Connection {

    public static final String BASE_URL = "http://10.0.2.2:8080";
    //public static final String BASE_URL = "http://172.21.7.188:8080";//Change for demo to localhost
    //public static final String BASE_URL = "http://143.92.200.10:8080";
    private static Retrofit retrofit = null;
    private static ApiInterface apiInterface = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
    public static ApiInterface getApiInterface(){
        if(apiInterface == null){
            apiInterface = getClient().create(ApiInterface.class);

        }
        return apiInterface;

    }
}