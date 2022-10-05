package com.findjb.findjob.Request;

import lombok.Data;

@Data
public class UpdateFreelancer {
    private String name;
    private String dob;
    private Integer gender;
    private String address;
    private String phone_number;
    private String introduction;
    private String avatar;
}
