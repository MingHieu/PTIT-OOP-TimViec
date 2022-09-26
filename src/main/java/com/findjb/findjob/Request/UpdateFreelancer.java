package com.findjb.findjob.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateFreelancer {
    String name;
    String dob;
    Integer gender;
    String address;
    String phone_number;
    String introduction;
}
