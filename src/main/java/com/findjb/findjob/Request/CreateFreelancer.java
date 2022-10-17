package com.findjb.findjob.Request;

import com.findjb.findjob.Constraint.Unique;

import lombok.Data;

@Data
public class CreateFreelancer {
    private String name;
    @Unique(message = "email đã tồn tại")
    private String email;
    private String password;
}
