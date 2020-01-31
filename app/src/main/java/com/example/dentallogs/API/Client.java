package com.example.dentallogs.API;

import com.example.dentallogs.UnsafeOkHttpClient;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Client {
    private Retrofit retrofit;
    private String BASE_URL = "https://4cb616ff.ngrok.io";
    private static Client mInstance;
    OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
    private Client() {
        Retrofit.Builder mBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = mBuilder.build();

    }

    public static synchronized Client getInstance() {
        if (mInstance == null) {
            mInstance = new Client();
        }
        return mInstance;
    }

    public Requests getApi() {
        return retrofit.create(Requests.class);
    }
}
