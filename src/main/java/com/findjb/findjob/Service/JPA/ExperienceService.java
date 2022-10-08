 
package com.findjb.findjob.Service.JPA;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findjb.findjob.JWT.JWTServices.UserDetailsImplement;
import com.findjb.findjob.Model.Experience;
import com.findjb.findjob.Model.Freelancer;
import com.findjb.findjob.Repositories.ExperienceRepository;
import com.findjb.findjob.Repositories.FreelancerRepository;
import com.findjb.findjob.Request.CreateExperience;
import com.findjb.findjob.Service.ExperienceServiceInterface;

@Service
public class ExperienceService implements ExperienceServiceInterface {

    @Autowired
    private FreelancerRepository freelancerRepository;
    @Autowired
    private ExperienceRepository skillRepository;

    @Override
    public void createExperience(CreateExperience updateExperience) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
        Freelancer freelancer = freelancerRepository.findById(userDetails.getId()).get();
        Experience experience = Experience.builder().name(updateExperience.getNamel())
                .position(updateExperience.getPosition())
                .from(updateExperience.getFrom())
                .to(updateExperience.getTo())
                .detail(updateExperience.getDetail())
                .freelancer(freelancer)
                .build();
        ExperienceRepository.save(experience);
    }

    @Override
    public void updateExperience(CreateExperience updateExperience, Long id) {
        Experience experience = ExperienceRepository.findById(id).get();
        
    }

    @Override
    public List<Experience> getListExperience() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Experience getListExperience(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteExperience(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createExperience(CreateExperience newExperience) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateExperience(CreateExperience updateExperience, Long id) {
        // TODO Auto-generated method stub
        
    }
    
}
