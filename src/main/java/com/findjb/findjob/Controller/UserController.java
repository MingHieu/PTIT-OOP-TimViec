package com.findjb.findjob.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.findjb.findjob.Request.EnterpriseRequest;
import com.findjb.findjob.Request.FreelancerRequest;
import com.findjb.findjob.Service.JPA.UserService;

@RestController
@RequestMapping({ "/api" })
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping({ "/hello" })
    public String getHello() {
        return "hello";
    }

    @PostMapping({ "/user/create/enterprise" })
    public ResponseEntity<Object> createNewEnterprise(@RequestBody EnterpriseRequest user) throws Exception {
        userService.createNewEnterprise(user);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("status", true);
        response.put("message", "create successfully");
        return new ResponseEntity<Object>(response, null, 200);
    }

    @PostMapping({ "/user/create/freelancer" })
    public ResponseEntity<Object> createNewFreelancer(@RequestBody FreelancerRequest freelancer) throws Exception {
        userService.createNewFreelancer(freelancer);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("status", true);
        response.put("message", "create successfully");
        return new ResponseEntity<Object>(response, null, 200);
    }
}
