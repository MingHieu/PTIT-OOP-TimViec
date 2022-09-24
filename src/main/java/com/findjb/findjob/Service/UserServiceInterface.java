package com.findjb.findjob.Service;

import com.findjb.findjob.Model.User;
import com.findjb.findjob.Request.EnterpriseRequest;
import com.findjb.findjob.Request.FreelancerRequest;

public interface UserServiceInterface {
    User getUser(Long id);

    void createNewEnterprise(EnterpriseRequest newEnterprise);

    void createNewFreelancer(FreelancerRequest newFreelancer);
}
