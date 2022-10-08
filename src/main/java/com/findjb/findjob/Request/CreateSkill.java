package com.findjb.findjob.Request;

import lombok.Data;

@Data
public class CreateSkill {
    private String name;
    private String description;
    private Double rating;
}
