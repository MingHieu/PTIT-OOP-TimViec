package com.example.timviec.model;

public class Experience {
    private String name = "";
    private String position = "";
    private String fromDate = "";
    private String toDate = "";
    private String description = "";

    public Experience() {
    }

    public Experience(String name, String position, String fromDate, String toDate, String description) {
        this.name = name;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
