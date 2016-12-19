package com.example.d062654.faciliman.Connection;

/**
 * Created by D062654 on 14.12.2016.
 */
import com.example.d062654.faciliman.Requests.IncidentRequest;
import com.example.d062654.faciliman.Requests.LoginRequest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;


public interface ApiInterface {
 //   @POST("/archieve/{incidentId}")
  //  Call<IncidentArchieve> sendIncidentArchieveRequest(@Path("api_key") String apiKey);

    @GET("/login/{username}/{password}")
    Call<ResponseBody> getLogin(@Path("username") String username, @Path("password") String password);

    @POST("incident/{username}")
    Call<ResponseBody> sendIncident(@Path("username") String username, @Body IncidentRequest input);

    @Multipart
    @POST("/incident/{username}/file")
    Call<ResponseBody> uploadPicture(@Path("username") String username, @Part MultipartBody.Part file, @Part("description") RequestBody description);
}
