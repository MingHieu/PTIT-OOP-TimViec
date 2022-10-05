package com.findjb.findjob.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.findjb.findjob.Request.EducationRequest;
import com.findjb.findjob.Responses.ObjectResponse;
import com.findjb.findjob.Responses.StatusResponse;
import com.findjb.findjob.Service.JPA.EducationService;

@RestController
@RequestMapping("/api/freelancer")
public class EducationController {
    @Autowired
    private EducationService educationService;

    @PostMapping("/create/education")
    public ResponseEntity<Object> createNewEducation(@RequestBody EducationRequest educationRequest) {
        educationService.createEducation(educationRequest);
        return new ResponseEntity<Object>(new StatusResponse(true, "Tạo mới thành công"), HttpStatus.OK);
    }

    @GetMapping("education/all")
    public ResponseEntity<Object> getAllEducation() {
        return new ResponseEntity<Object>(
                new ObjectResponse(true, "Lấy danh sách thành công", educationService.getListEducation()),
                HttpStatus.OK);
    }

    @GetMapping("education/{id}")
    public ResponseEntity<Object> getDetailEducation(@RequestParam Long id) {
        return new ResponseEntity<Object>(
                new ObjectResponse(true, "Lấy danh sách thành công", educationService.getDetailEducation(id)),
                HttpStatus.OK);
    }

    @PutMapping("education/update/{id}")
    public ResponseEntity<Object> updateEducation(@RequestParam Long id,
            @RequestBody EducationRequest educationRequest) {
        educationService.updateEducation(educationRequest, id);
        return new ResponseEntity<Object>(new StatusResponse(true, "Cập nhật thành công"), HttpStatus.OK);
    }

    @DeleteMapping("education/delelte/{id}")
    public ResponseEntity<Object> delelteEducation(@RequestParam Long id) {
        educationService.deleteEducation(id);
        return new ResponseEntity<Object>(new StatusResponse(true, "Xóaz thành công"), HttpStatus.OK);
    }
}
