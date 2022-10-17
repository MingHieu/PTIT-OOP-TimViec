package com.example.timviec.model;

import com.google.gson.internal.LinkedTreeMap;

import java.util.LinkedHashMap;

public class API {
    public static class LoginBody {
        private String username;
        private String password;

        public LoginBody(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    public static class LoginResponse {
        private int id, role;
        private String email, token;
        private LinkedHashMap detail;

        public int getRole() {
            return role;
        }

        public String getToken() {
            return token;
        }

        public LinkedHashMap getDetail() {
            return detail;
        }
    }

    public static class UpdateFreelancerBody {
        private String avatar, name, dob, address, phone_number, introduction;
        private int gender;

        public UpdateFreelancerBody(String avatar, String name, String dob, String address, String phone_number, String introduction, int gender) {
            this.avatar = avatar;
            this.name = name;
            this.dob = dob;
            this.address = address;
            this.phone_number = phone_number;
            this.introduction = introduction;
            this.gender = gender;
        }
    }

    public static class UpdateFreelancerResponse {
        private Boolean status;
        private String message;

        public String getMessage() {
            return message;
        }
    }

    public static class UserDetailResponse {
        private Boolean status;
        private String message;
        private LinkedHashMap data;

        public LinkedHashMap getData() {
            return data;
        }
    }

}
