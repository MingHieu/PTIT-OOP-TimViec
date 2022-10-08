package com.findjb.findjob.Service.JPA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.findjb.findjob.JWT.JWTServices.UserDetailsImplement;
import com.findjb.findjob.Model.Experience;
import com.findjb.findjob.Model.Freelancer;
import com.findjb.findjob.Model.Skill;
import com.findjb.findjob.Repositories.ExperienceRepository;
import com.findjb.findjob.Repositories.FreelancerRepository;
import com.findjb.findjob.Repositories.SkillRepository;
import com.findjb.findjob.Request.CreateSkill;
import com.findjb.findjob.Service.SkillServiceInterface;

@Service
public class SkillService implements SkillServiceInterface{
    @Autowired
    private FreelancerRepository freelancerRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Override
    public void createNewSkill(CreateSkill updateSkill) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
        Freelancer freelancer = freelancerRepository.findById(userDetails.getId()).get();
        Skill skill = Skill.builder().name(updateSkill.getName())
                .description(updateSkill.getDescription())
                .rating(updateSkill.getRating())
                .freelancer(freelancer)
                .build();
        skillRepository.save(skill);
        
    }
    @Override
    public void updateSkill(CreateSkill UpdateSkills, Long id) {
        Skill skill = skillRepository.findById(id).get();
        skill.setDescription(skill.getDescription());
        skill.setRating(skill.getRating());
        skill.setName(skill.getName());
        skillRepository.save(skill);
    }
    @Override
    public List<Skill> getListSkill() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
        Freelancer freelancer = freelancerRepository.findById(userDetails.getId()).get();
        return skillRepository.findByFreelancerId(freelancer.getId());
    }
    
    @Override
    public Skill getDetailSkill() {
        return SkillRepository.findById(id).get();
    @Override
    public void deleteSKill() {
        SkillRepository.deleteById(id);
        
    }
    
}
