package com.example.timviec.model;

public class User {
    int roleId;
    Object detail;

    public User() {
    }

    public User(int roleId, Object detail) {
        this.roleId = roleId;
        this.detail = detail;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "User{" +
                "roleId=" + roleId +
                ", detail=" + detail +
                '}';
    }
}

