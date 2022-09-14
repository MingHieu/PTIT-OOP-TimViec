package com.example.timviec.model;

public class Education {
    private String name = "";
    private String major = "";
    private String from = "";
    private String to = "";
    private String detail = "";

    public Education() {
    }

    public Education(String name, String major, String from, String to, String detail) {
        this.name = name;
        this.major = major;
        this.from = from;
        this.to = to;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
