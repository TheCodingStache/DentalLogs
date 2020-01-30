package com.example.dentallogs.API;


import com.example.dentallogs.Model.ModelLogin;
import com.example.dentallogs.Model.ModelSignUp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Requests {
    @POST("/login")
    @FormUrlEncoded
    Call<ModelLogin> login(
            @Field("username") String receiveEmail,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("actions/id_register")
    Call<ModelSignUp> signup(
            @Field("email") String email,
            @Field("username") String identification,
            @Field("password") String surname,
            @Field("name") String lastnamelatin,
            @Field("lastName") String firstname,
            @Field("officeName") String firstnameLatin,
            @Field("location") String born_location
    );
}
