package com.findjb.findjob.Service;

import java.util.List;

import com.findjb.findjob.Model.Experience;
import com.findjb.findjob.Request.CreateExperience;

public interface ExperienceServiceInterface {
    void createExperience(CreateExperience newExperience);

    void updateExperience(CreateExperience updateExperience, Long id );

    List<Experience> getListExperience();

    Experience getListExperience (Long id);

    void deleteExperience(Long id);
}
