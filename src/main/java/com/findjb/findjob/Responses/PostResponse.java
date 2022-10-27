package com.findjb.findjob.Responses;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {
    String name;
    String companyName;
    String comapyAvatar;
    String salary;
    String type;
    Integer quantity;
    String experience;
    String position;
    String gender;
    String address;
    String description;
    String requirement;
    String benefit;
    Date createAt;
    Date expired;
}
