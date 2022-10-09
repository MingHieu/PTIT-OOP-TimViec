package com.example.timviec.model;

import java.util.LinkedHashMap;

public class User {
    int roleId;
    LinkedHashMap detail;

    public User() {
    }

    public User(int roleId, LinkedHashMap detail) {
        this.roleId = roleId;
        this.detail = detail;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public LinkedHashMap getDetail() {
        return detail;
    }

    public void setDetail(LinkedHashMap detail) {
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

