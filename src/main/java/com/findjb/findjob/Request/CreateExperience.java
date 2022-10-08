package com.findjb.findjob.Request;
import lombok.Data;

@Data
public class CreateExperience {
    private Integer freelencer;
    private String namel;
    private String position;
    private String from;
    private String to;
    private String detail;
}
