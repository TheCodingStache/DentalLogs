package com.example.dentallogs.API;


import com.example.dentallogs.Model.ModelLogin;
import com.example.dentallogs.Model.ModelSignUp;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Requests {
    @POST("doctor/signin")
    Call<ModelLogin[]> login(
            @Body JsonObject jsonObject
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
