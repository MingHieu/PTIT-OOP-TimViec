package com.findjb.findjob.Request;

import lombok.Data;
@Data
public class EnterpriseRequest {
    private String name;
    private String email;
    private String password;
    private String introduction;
    private String address;
}
