package com.findjb.findjob.Request;

import lombok.Data;

@Data
public class PostRequest {
    String name;
    String salary;
    Integer quantity;
    String description;
    String requirement;
    String benefit;
}
