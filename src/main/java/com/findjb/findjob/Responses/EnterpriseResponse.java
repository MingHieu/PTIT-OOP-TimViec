package com.findjb.findjob.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnterpriseResponse {
    private String name;
    private String email;
    private String introduction;
    private String address;
    private String url;
}
