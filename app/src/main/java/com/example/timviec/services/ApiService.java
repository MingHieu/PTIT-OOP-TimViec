package com.example.timviec.services;

import com.example.timviec.App;
import com.example.timviec.model.API;
import com.example.timviec.model.Education;
import com.example.timviec.model.Experience;
import com.example.timviec.model.Skill;
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
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    @POST("auth/login")
    Call<API.LoginResponse> login(@Body API.LoginBody body);

    @GET("auth/user/detail")
    Call<API.UserDetailResponse> getUserDetail();

    @PUT("user/freelancer/update")
    Call<API.Response> updateFreelancer(@Body API.UpdateFreelancerBody body);

    @POST("education/create")
    Call<API.Response> createEducation(@Body Education body);

    @PUT("education/update/{id}")
    Call<API.Response> updateEducation(@Body Education body, @Path("id") Integer id);

    @DELETE("education/delete/{id}")
    Call<API.Response> deleteEducation(@Path("id") Integer id);

    @POST("skill/create")
    Call<API.Response> createSkill(@Body Skill body);

    @PUT("skill/update/{id}")
    Call<API.Response> updateSkill(@Body Skill body, @Path("id") Integer id);

    @DELETE("skill/delete/{id}")
    Call<API.Response> deleteSkill(@Path("id") Integer id);

    @POST("experience/create")
    Call<API.Response> createExperience(@Body Experience body);

    @PUT("experience/update/{id}")
    Call<API.Response> updateExperience(@Body Experience body, @Path("id") Integer id);

    @DELETE("experience/delete/{id}")
    Call<API.Response> deleteExperience(@Path("id") Integer id);
}

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



