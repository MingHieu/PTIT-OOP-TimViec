package com.findjb.findjob.Request;

import lombok.Data;

@Data
public class UpdateEnterprise {
    private String name;
    private String address;
    private String phone_number;
    private String introduction;
}