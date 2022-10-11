package com.findjb.findjob.Request;
import lombok.Data;
@Data
public class UpdateSkills {
    private Long id;
    private String name;
    private String description;
    private Double rating;
    private Integer freelancer_id;
}
