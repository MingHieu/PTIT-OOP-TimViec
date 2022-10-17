package com.example.timviec.model;

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

    public static class Response {
        private Boolean status;
        private String message;

        public String getMessage() {
            return message;
        }
    }
}
