package com.findjb.findjob.Request;

import lombok.Data;

@Data
public class UpdateFreelancer {
    String name;
    String dob;
    Integer gender;
    String address;
    String phone_number;
    String introduction;
}
