package com.findjb.findjob.Service.JPA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.findjb.findjob.Model.Enterprise;
import com.findjb.findjob.Model.Role;
import com.findjb.findjob.Model.User;
import com.findjb.findjob.Repositories.EnterpriseRepository;
import com.findjb.findjob.Repositories.RoleRepository;
import com.findjb.findjob.Repositories.UserRepository;
import com.findjb.findjob.Request.CreateEnterprise;
import com.findjb.findjob.Request.UpdateEnterprise;
import com.findjb.findjob.Service.EnterpriseServiceInterface;

@Service
public class EnterpriseService implements EnterpriseServiceInterface {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public void createNewEnterprise(CreateEnterprise newEnterprise) {
        Optional<Role> role = this.roleRepository.findById(2L);
        Role thisRole = role.get();
        User user = User.builder().username(newEnterprise.getEmail())
                .password(this.passwordEncoder.encode(newEnterprise.getPassword())).role(thisRole)
                .build();
        userRepository.save(user);
        Enterprise enterprise = Enterprise.builder()
                .name(newEnterprise.getName())
                .address(newEnterprise.getAddress())
                .email(newEnterprise.getEmail())
                .user(user)
                .build();
        enterpriseRepository.save(enterprise);
    }

    @Override
    public void updateEnterprise(UpdateEnterprise updateEnterprise, long id) {
        Enterprise enterprise = enterpriseRepository.findById(id).get();
        enterprise.setAddress(updateEnterprise.getAddress());
        enterprise.setIntroduction(updateEnterprise.getIntroduction());
    }

    @Override
    public void deleteEnterprise(Long id) {
        enterpriseRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> getAllEnterprise(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Enterprise> enterprises = enterpriseRepository.findAll(pageable);
        List<Enterprise> list = new ArrayList<Enterprise>();
        list = enterprises.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("data", list);
        response.put("currentPage", enterprises.getNumber());
        response.put("totalItems", enterprises.getTotalElements());
        response.put("totalPages", enterprises.getTotalPages());
        return response;
    }

    @Override
    public Enterprise getEnterpriseDetails(Long id) {
        Enterprise enterprise = enterpriseRepository.findById(id).get();
        return enterprise;
    }

}
