package com.example.timviec.model;

public class User {
    Freelancer freelancer;
    Enterprise enterprise;
    private int roleId;
    private String username;

    public User() {
    }

    public User(int roleId, String username, Freelancer freelancer, Enterprise enterprise) {
        this.roleId = roleId;
        this.username = username;
        this.freelancer = freelancer;
        this.enterprise = enterprise;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    public String toString() {
        return "User{" +
                "roleId=" + roleId +
                ", username='" + username + '\'' +
                ", freelancer=" + freelancer +
                ", enterprise=" + enterprise +
                '}';
    }
}

