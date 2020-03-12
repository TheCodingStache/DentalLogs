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
    Call<ModelLogin> login(
            @Body JsonObject jsonObject
    );

    @POST("doctor/signup")
    Call<ModelSignUp> signup(
            @Body JsonObject jsonObject
    );
}
