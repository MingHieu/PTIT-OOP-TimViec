package com.findjb.findjob.Request;

import lombok.Data;

@Data
public class EducationRequest {
    private String name;
    private String major;
    private String from;
    private String to;
    private String description;
}
