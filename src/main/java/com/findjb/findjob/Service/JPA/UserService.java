package com.findjb.findjob.Service.JPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.findjb.findjob.JWT.JWTServices.UserDetailsImplement;
import com.findjb.findjob.Model.User;
import com.findjb.findjob.Repositories.UserRepository;
import com.findjb.findjob.Request.FcmToken;
import com.findjb.findjob.Service.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void setFcmToken(FcmToken fcmToken) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
        User user = entityManager.find(User.class, userDetails.getId());
        user.setFcmToken(fcmToken.getFcmToken());
        userRepository.save(user);
    }
}
