package com.example.timviec.model;

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
        private String email, accessToken;
        /**
         * Example :
         * "id": 1,
         * "name": "Hưng",
         * "dob": null,
         * "gender": null,
         * "address": null,
         * "phone_number": null,
         * "email": "hung@gmail.com",
         * "introduction": null,
         * "level": null,
         * "avatar": null
         */
        private LinkedHashMap detail;

        public int getRole() {
            return role;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public LinkedHashMap getDetail() {
            return detail;
        }

        @Override
        public String toString() {
            return "LoginResponse{" +
                    "id=" + id +
                    ", role=" + role +
                    ", email='" + email + '\'' +
                    ", accessToken='" + accessToken + '\'' +
                    ", detail=" + detail +
                    '}';
        }
    }
}
