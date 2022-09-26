package com.findjb.findjob.Service;

import com.findjb.findjob.Request.CreateEnterprise;
import com.findjb.findjob.Request.UpdateEnterprise;

public interface EnterpriseServiceInterface {
    void createNewEnterprise(CreateEnterprise newEnterprise);

    void updateEnterprise(UpdateEnterprise updateEnterprise, long id);

    void deleteEnterprise(Long id);
}
