package com.findjb.findjob.Service;

import java.util.List;

import com.findjb.findjob.Model.Freelancer;
import com.findjb.findjob.Model.Post;
import com.findjb.findjob.Request.CreateFreelancer;
import com.findjb.findjob.Request.UpdateFreelancer;

public interface FreelancerServiceInterface {
    void createNewFreelancer(CreateFreelancer newFreelancer);

    void updateFreelancer(UpdateFreelancer updateFreelancer);

    void deleteFreelancer();

    Freelancer getFreelancerDetail(Long id);

    List<Post> getAllPostApply();
}
