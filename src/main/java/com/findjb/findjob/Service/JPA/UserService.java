package com.findjb.findjob.Service.JPA;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.findjb.findjob.Model.Enterprise;
import com.findjb.findjob.Model.Freelancer;
import com.findjb.findjob.Model.Role;
import com.findjb.findjob.Model.User;
import com.findjb.findjob.Repositories.EnterpriseRepository;
import com.findjb.findjob.Repositories.FreelancerRepository;
import com.findjb.findjob.Repositories.RoleRepository;
import com.findjb.findjob.Repositories.UserRepository;
import com.findjb.findjob.Request.EnterpriseRequest;
import com.findjb.findjob.Request.FreelancerRequest;
import com.findjb.findjob.Service.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {
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
    @Autowired
    private FreelancerRepository freelancerRepository;

    // public UserService(UserRepository userRepository, RoleRepository
    // roleRepository,
    // BCryptPasswordEncoder passwordEncoder) {
    // this.userRepository = userRepository;
    // this.roleRepository = roleRepository;
    // this.passwordEncoder = passwordEncoder;
    // }

    @Override
    public User getUser(Long id) {

        // User user = userRepository.findById(id).get();
        // Enterprise enterprise = entityManager.find(Enterprise.class, user.getId());
        return null;
    }

    @Override
    public void createNewEnterprise(EnterpriseRequest newUser) {
        Optional<Role> role = this.roleRepository.findById(2L);
        Role thisRole = role.get();
        User user = User.builder().username(newUser.getEmail())
                .password(this.passwordEncoder.encode(newUser.getPassword())).role(thisRole)
                .build();
        userRepository.save(user);
        Enterprise enterprise = Enterprise.builder()
                .name(newUser.getName())
                .address(newUser.getAddress())
                .email(newUser.getEmail())
                .introduction(newUser.getIntroduction())
                .user(user)
                .build();
        enterpriseRepository.save(enterprise);
    }

    @Override
    public void createNewFreelancer(FreelancerRequest newFreelancer) {
        Optional<Role> role = this.roleRepository.findById(1L);
        Role freelancerRole = role.get();
        User user = User.builder().username(newFreelancer.getEmail())
                .password(this.passwordEncoder.encode(newFreelancer.getPassword())).role(freelancerRole)
                .build();
        userRepository.save(user);
        Freelancer freelancer = Freelancer.builder()
                .name(newFreelancer.getName())
                .address(newFreelancer.getAddress())
                .dob(newFreelancer.getDob())
                .email(newFreelancer.getEmail())
                .phone_number(newFreelancer.getPhone_number())
                .gender(newFreelancer.getGender())
                .introduction(newFreelancer.getIntroduction())
                .level(newFreelancer.getLevel())
                .user(user)
                .build();
        freelancerRepository.save(freelancer);
    }
}
