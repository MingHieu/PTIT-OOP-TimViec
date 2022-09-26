package com.findjb.findjob.Service.JPA;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.findjb.findjob.Model.Freelancer;
import com.findjb.findjob.Model.Role;
import com.findjb.findjob.Model.User;
import com.findjb.findjob.Repositories.FreelancerRepository;
import com.findjb.findjob.Repositories.RoleRepository;
import com.findjb.findjob.Repositories.UserRepository;
import com.findjb.findjob.Request.CreateFreelancer;
import com.findjb.findjob.Request.UpdateFreelancer;
import com.findjb.findjob.Service.FreelancerServiceInterface;

@Service
public class FreelancerService implements FreelancerServiceInterface {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FreelancerRepository freelancerRepository;

    @Override
    public void createNewFreelancer(CreateFreelancer newFreelancer) {
        Optional<Role> role = this.roleRepository.findById(1L);
        Role freelancerRole = role.get();
        User user = User.builder().username(newFreelancer.getEmail())
                .password(this.passwordEncoder.encode(newFreelancer.getPassword())).role(freelancerRole)
                .build();
        userRepository.save(user);
        Freelancer freelancer = Freelancer.builder()
                .name(newFreelancer.getName())
                .email(newFreelancer.getEmail())
                .user(user)
                .build();
        freelancerRepository.save(freelancer);
    }

    @Override
    public void updateFreelancer(UpdateFreelancer updateFreelancer, Long id) {
        Freelancer freelancer = freelancerRepository.findById(id).get();
        freelancer.setAddress(updateFreelancer.getAddress());
        freelancer.setName(updateFreelancer.getName());
        freelancer.setDob(updateFreelancer.getDob());
        freelancer.setGender(updateFreelancer.getGender());
        freelancer.setPhone_number(updateFreelancer.getPhone_number());
        freelancer.setIntroduction(updateFreelancer.getIntroduction());
        freelancerRepository.save(freelancer);
    }

    @Override
    public void deleteFreelancer(Long id) {
        freelancerRepository.deleteById(id);
    }

}
