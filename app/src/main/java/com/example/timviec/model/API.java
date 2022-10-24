package com.example.timviec.model;

import java.util.ArrayList;

public class API {
    public static class LoginBody {
        private String username, password;

        public LoginBody(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    public static class LoginResponse {
        private int id, role;
        private String email, token;
        private User.UserDetail detail;

        public int getRole() {
            return role;
        }

        public String getToken() {
            return token;
        }

        public User.UserDetail getDetail() {
            return detail;
        }
    }

    public static class CreateFreelancerBody {
        private String name;
        private String email;
        private String password;

        public CreateFreelancerBody(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }

    public static class SignupUserResponse {
        private int ID;
        private int role;
        private String email;
        private String token;
        private User.UserDetail detail;

        public int getRole() {
            return role;
        }

        public String getEmail() {
            return email;
        }

        public String getToken() {
            return token;
        }
    }

    public static class UserDetailResponse {

        private Boolean status;
        private String message;
        private UserDetailResponseData data;

        public UserDetailResponseData getData() {
            return data;
        }

        public static class UserDetailResponseData {
            private int role;
            private User.UserDetail detail;

            public int getRole() {
                return role;
            }

            public User.UserDetail getDetail() {
                return detail;
            }
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

    public static class UpdateEnterpriseBody {
        private String avatar, name, address, phone_number, introduction;

        public UpdateEnterpriseBody(String avatar, String name, String address, String phone_number, String introduction) {
            this.avatar = avatar;
            this.name = name;
            this.address = address;
            this.phone_number = phone_number;
            this.introduction = introduction;
        }
    }

    public static class UpdateFCMBody {
        private String fcm;

        public UpdateFCMBody(String fcm) {
            this.fcm = fcm;
        }
    }

    public static class Response {
        private Boolean status;
        private String message;

        public String getMessage() {
            return message;
        }
    }

    public static class getAllEducationResponse {
        private Boolean status;
        private String message;
        private ArrayList<Education> data;

        public ArrayList<Education> getData() {
            return data;
        }
    }

    public static class getAllSkillResponse {
        private Boolean status;
        private String message;
        private ArrayList<Skill> data;

        public ArrayList<Skill> getData() {
            return data;
        }
    }

    public static class getAllExperienceResponse {
        private Boolean status;
        private String message;
        private ArrayList<Experience> data;

        public ArrayList<Experience> getData() {
            return data;
        }
    }
}
