package com.example.timviec.model;

public class Skill {
    private String name = "";
    private int rate = 0;
    private String description = "";

    public Skill() {
    }

    public Skill(String name, int rate, String description) {
        this.name = name;
        this.rate = rate;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
