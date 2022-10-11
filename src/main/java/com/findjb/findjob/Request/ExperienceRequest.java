package com.findjb.findjob.Request;

import lombok.Data;

@Data
public class ExperienceRequest {
    String name;
    String position;
    String from_date;
    String to_date;
    String detail;
}
