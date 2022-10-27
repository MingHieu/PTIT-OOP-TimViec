package com.findjb.findjob.Responses;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {
    String name;
    String companyName;
    String companyAvatar;
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
    Date create_at;
    Date expired;
}
