package com.example.timviec.model;

import com.google.gson.annotations.SerializedName;

public class API {
    public static class LoginBody {
        @SerializedName("username")
        private String username;
        @SerializedName("password")
        private String password;

        public LoginBody(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    public static class LoginResponse {
        @SerializedName("username")
        private String username;
        @SerializedName("password")
        private String password;

        public LoginResponse(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
