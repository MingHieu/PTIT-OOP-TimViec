package com.findjb.findjob.Controller;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findjb.findjob.Model.Role;
import com.findjb.findjob.Repositories.RoleRepository;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/test")
public class HelloController {
    @Autowired
    private EntityManager entityManager;

    @GetMapping({ "/hello" })
    public String Data() {
        Role resultList = entityManager.createQuery("SELECT r FROM Role r WHERE r.name like :name",Role.class)
                .setParameter("name", "%n%").setMaxResults(1).getSingleResult();
        return resultList.getName();
    }
}