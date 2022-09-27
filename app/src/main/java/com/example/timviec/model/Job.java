package com.example.timviec.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Job {
    private String name = "";
    private String expectSalary = "";
    private int quantity = 0;
    private String description = "";
    private String requirement = "";
    private String benefit = "";
    private Date createAt;
    private Date expired;

    public Job() {
    }

    public Job(String name, String expectSalary, int quantity, String description, String requirement, String benefit, String createAt, String expired) {
        this.name = name;
        this.expectSalary = expectSalary;
        this.quantity = quantity;
        this.description = description;
        this.requirement = requirement;
        this.benefit = benefit;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
            this.expired = simpleDateFormat.parse(expired);
        } catch (Exception e) {

        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
            this.createAt = simpleDateFormat.parse(createAt);
        } catch (Exception e) {

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpectSalary() {
        return expectSalary;
    }

    public void setExpectSalary(String expectSalary) {
        this.expectSalary = expectSalary;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDesription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
            this.createAt = simpleDateFormat.parse(createAt);
        } catch (Exception e) {

        }
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
            this.expired = simpleDateFormat.parse(expired);
        } catch (Exception e) {

        }
    }
}
