package com.example.timviec.model;

public class Skill {
    private String name = "";
    private float rating = 0;
    private String description = "";

    public Skill() {
    }

    public Skill(String name, float rating, String description) {
        this.name = name;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
