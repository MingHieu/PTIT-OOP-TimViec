package com.example.timviec.services;

import com.example.timviec.App;
import com.example.timviec.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

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

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    OkHttpClient apiClient = new OkHttpClient().newBuilder()
            .addInterceptor(new ApiServiceInterceptor())
            .build();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://timviecnhanh.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(apiClient)
            .build()
            .create(ApiService.class);

    // Auth Api
    @POST("login")
    Call<User> login(@Body LoginBody body);

    @GET("hello")
    Call<User> hello();
}

class ApiServiceInterceptor implements Interceptor {

    private String auth_token;

    public ApiServiceInterceptor() {
        this.auth_token = App.getContext().getStateManager().getAuthToken();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        if (this.auth_token != null) {
            builder.addHeader("Authorization", this.auth_token);
        }
        builder.addHeader("Content-Type", "application/json");
        return chain.proceed(builder.build());
    }
}

class LoginBody {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public LoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

