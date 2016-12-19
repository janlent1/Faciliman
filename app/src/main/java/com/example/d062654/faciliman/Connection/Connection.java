package com.example.d062654.faciliman.Connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Connection {

    public static final String BASE_URL = "http://10.0.2.2:8080";//Change for demo to localhost
    private static Retrofit retrofit = null;
    private static ApiInterface apiInterface = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
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