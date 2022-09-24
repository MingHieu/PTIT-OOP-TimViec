package com.findjb.findjob.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findjb.findjob.JWT.JwtUtils;
import com.findjb.findjob.JWT.JWTServices.UserDetailsImplement;
import com.findjb.findjob.Model.Enterprise;
import com.findjb.findjob.Model.Freelancer;
import com.findjb.findjob.Repositories.RoleRepository;
import com.findjb.findjob.Repositories.UserRepository;
import com.findjb.findjob.Request.LoginRequest;
import com.findjb.findjob.Responses.EnterpriseResponse;
import com.findjb.findjob.Responses.FreelancerResponse;
import com.findjb.findjob.Responses.JwtResponse;

@RestController
@RequestMapping({ "/api/auth" })
public class AuthController {

        @PersistenceContext
        private EntityManager entityManager;

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        UserRepository userRepository;

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        PasswordEncoder encoder;

        @Autowired
        JwtUtils jwtUtils;

        @PostMapping({ "/login" })
        public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                                .map(item -> item.getAuthority())
                                .collect(Collectors.toList());
                String role = roles.get(0);
                Long role_id = roleRepository.findByName(role).getId();
                Object detail = new Object();
                if (role_id == 2) {
                        Enterprise enterprise = entityManager.find(Enterprise.class, userDetails.getId());
                        EnterpriseResponse enterpriseResponse = new EnterpriseResponse(enterprise.getName(),
                                        enterprise.getEmail(),
                                        enterprise.getIntroduction(), enterprise.getAddress());
                        detail = enterpriseResponse;
                } else {
                        Freelancer freelancer = entityManager.find(Freelancer.class, userDetails.getId());
                        FreelancerResponse freelancerResponse = new FreelancerResponse(freelancer.getName(),
                                        freelancer.getDob(),
                                        freelancer.getGender(), freelancer.getAddress(), freelancer.getPhone_number(),
                                        freelancer.getEmail(), freelancer.getIntroduction(), freelancer.getLevel());
                        detail = freelancerResponse;
                }
                return ResponseEntity.ok(new JwtResponse(jwt,
                                userDetails.getId(),
                                userDetails.getUsername(),
                                detail,
                                role_id));
        }

        @GetMapping({ "/smt" })
        public Object responseString() {
                return entityManager
                                .createQuery("SELECT * FROM freelancers e WHERE e.user_id = :user_id", Freelancer.class)
                                .setParameter("user_id", 2)
                                .getSingleResult();
        }
}
