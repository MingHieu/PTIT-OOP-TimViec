package com.findjb.findjob.Request;

import lombok.Data;

@Data
public class FreelancerRequest {
    private String name;
    private String dob;
    private Integer gender;
    private String address;
    private String phone_number;
    private String email;
    private String password;
    private String introduction;
    private Integer level;
}
