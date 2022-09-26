package com.findjb.findjob.Request;

import lombok.Data;

@Data
public class CreateFreelancer {
    private String name;
    private String email;
    private String password;
}
