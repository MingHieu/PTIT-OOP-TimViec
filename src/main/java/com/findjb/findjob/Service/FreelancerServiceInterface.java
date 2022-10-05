package com.findjb.findjob.Service;

import com.findjb.findjob.Request.CreateFreelancer;
import com.findjb.findjob.Request.UpdateFreelancer;

public interface FreelancerServiceInterface {
    void createNewFreelancer(CreateFreelancer newFreelancer);
    void updateFreelancer(UpdateFreelancer updateFreelancer);
    void deleteFreelancer();

}
