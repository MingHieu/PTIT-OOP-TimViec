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
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

class ApiServiceInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String authToken = "Bearer " + App.getContext().getStateManager().getAuthToken();
        builder.header("Authorization", authToken);
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


    @GET("auth/user/detail")
    Call<API.UserDetailResponse> getUserDetail();a

    @POST("auth/login")
    Call<API.LoginResponse> login(@Body API.LoginBody body);

    @PUT("user/freelancer/update")
    Call<API.UpdateFreelancerResponse> updateFreelancer(@Body API.UpdateFreelancerBody body);

}




