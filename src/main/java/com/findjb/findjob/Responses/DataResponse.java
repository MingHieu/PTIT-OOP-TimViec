package com.findjb.findjob.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataResponse {
    Long role;
    Object detail;
}