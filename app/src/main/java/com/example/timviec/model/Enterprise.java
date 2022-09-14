package com.example.timviec.model;

public class Enterprise {
    private String name = "";
    private String address = "";
    private String email = "";
    private String introduce = "";
    private String enterpriseUrl = "";

    public Enterprise() {
    }

    public Enterprise(String name, String address, String email, String introduce, String enterpriseUrl) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.introduce = introduce;
        this.enterpriseUrl = enterpriseUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getEnterpriseUrl() {
        return enterpriseUrl;
    }

    public void setEnterpriseUrl(String enterpriseUrl) {
        this.enterpriseUrl = enterpriseUrl;
    }
}
