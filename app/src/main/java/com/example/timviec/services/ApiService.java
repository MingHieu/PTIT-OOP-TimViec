package com.example.timviec.services;

import com.example.timviec.App;
import com.example.timviec.model.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

class ApiServiceInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String authToken = App.getContext().getStateManager().getAuthToken();
        if (authToken != null) {
            builder.addHeader("Authorization", authToken);
        }
        builder.addHeader("Content-Type", "application/json");
        return chain.proceed(builder.build());
    }
}

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    OkHttpClient apiClient = new OkHttpClient().newBuilder()
            .addInterceptor(new ApiServiceInterceptor())
            .build();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://timviecnhanh.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(apiClient)
            .build()
            .create(ApiService.class);

    // Auth Api
    @POST("auth/login")
    Call<API.LoginResponse> login(@Body API.LoginBody body);
}




