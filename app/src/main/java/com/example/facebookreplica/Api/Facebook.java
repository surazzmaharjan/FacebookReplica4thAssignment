package com.example.facebookreplica.Api;

import com.example.facebookreplica.Model.ApiUser;
import com.example.facebookreplica.Model.TimelineData;
import com.example.facebookreplica.serverresponse.ImageResponse;
import com.example.facebookreplica.serverresponse.SignupResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Facebook {


    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadProfileImage(@Part MultipartBody.Part img);


    @POST("register")
    Call<Void> registerUser(@Body ApiUser user);


    @FormUrlEncoded
    @POST("apilogin")
    Call<SignupResponse> checkUser(@Field("email") String email, @Field("password") String password);

    @GET("users/me")
    Call<ApiUser> getUserDetails(@Header("Authorization")String token);


    @GET("timelines")
    Call<List<TimelineData>> getTimelines(@Header("Authorization")String token);


}
