package com.findjb.findjob.Service;

import java.util.List;

import com.findjb.findjob.Model.Skill;
import com.findjb.findjob.Request.CreateSkill;

public interface SkillServiceInterface {
    void createNewSkill(CreateSkill updateSkill);

    void updateSkill(CreateSkill UpdateSkills,Long id);

    List<Skill> getListSkill();

    Skill getDetailSkill (Long id);

    void deleteSKill(Long id);
}
