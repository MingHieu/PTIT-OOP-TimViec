package com.example.timviec.model;

public class Education {
    private String name = "";
    private String major = "";
    private String fromDate = "";
    private String toDate = "";
    private String description = "";

    public Education() {
    }

    public Education(String name, String major, String fromDate, String toDate, String description) {
        this.name = name;
        this.major = major;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.description = description;
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
