package com.findjb.findjob.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.findjb.findjob.Request.CreateEnterprise;
import com.findjb.findjob.Service.JPA.EnterpriseService;

@RestController
@RequestMapping("/api/user")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping({ "/create/enterprise" })
    public ResponseEntity<Object> createNewEnterprise(@Valid @RequestBody CreateEnterprise user) {
        enterpriseService.createNewEnterprise(user);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("status", true);
        response.put("message", "create successfully");
        return new ResponseEntity<Object>(response, null, 200);
    }

    @PutMapping("/enterprise/update")
    public ResponseEntity<Object> updateEnterprise() {
        Object ob = new String("abc");
        return new ResponseEntity<Object>(ob, HttpStatus.OK);
    }

    @DeleteMapping("/enterprise/delete")
    public ResponseEntity<Object> deleteEnterprise() {
        enterpriseService.deleteEnterprise();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("status", true);
        response.put("message", "create successfully");
        return new ResponseEntity<Object>(response, null, 200);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllEnterprise(@RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "2") Integer pageSize) {
        return new ResponseEntity<Object>(enterpriseService.getAllEnterprise(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEnterpriseDetail(@PathVariable Long id) {
        return new ResponseEntity<Object>(enterpriseService.getEnterpriseDetails(id), HttpStatus.OK);
    }
}
