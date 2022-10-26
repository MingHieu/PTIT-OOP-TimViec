package com.findjb.findjob.Request;

import java.util.Date;

import lombok.Data;

@Data
public class PostRequest {
    String name;
    String salary;
    Integer quantity;
    String description;
    String requirement;
    String benefit;
    String experience;
    String position;
    String type;
    Date expired;
}
