package com.findjb.findjob.Controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class HelloController {
    @GetMapping({ "/hello" })
    public String Data() {
        HashMap<String,String> data = new HashMap<>();
        data.put("id", "1");
        data.put("name", "Hung");
        String response = new Gson().toJson(data);
        return response;
    }
}