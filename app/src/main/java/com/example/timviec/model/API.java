package com.example.timviec.model;

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
        private String email, accessToken, tokenType;
        private Object detail;

        public int getRole() {
            return role;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public Object getDetail() {
            return detail;
        }

        @Override
        public String toString() {
            return "LoginResponse{" +
                    "id=" + id +
                    ", role=" + role +
                    ", email='" + email + '\'' +
                    ", accessToken='" + accessToken + '\'' +
                    ", tokenType='" + tokenType + '\'' +
                    ", detail=" + detail +
                    '}';
        }
    }
}
