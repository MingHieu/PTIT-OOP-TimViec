package com.findjb.findjob.Responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    /**
     * @param httpStatus
     * @param status
     * @param message
     * @param obj
     * @return
     */
    public static ResponseEntity<?> generateResponse(HttpStatus httpStatus, boolean status,String message ,Object obj) {
        Map<String,Object> response = new HashMap<String,Object>();
            response.put("status", status);
            response.put("message", message);
            response.put("data", obj);
            return new ResponseEntity<>(response, httpStatus);
    }
}
