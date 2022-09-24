package com.findjb.findjob.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FreelancerResponse {
    private String name;
    private String dob;
    private Integer gender;
    private String address;
    private String phone_number;
    private String email;
    private String introduction;
    private Integer level;
}
