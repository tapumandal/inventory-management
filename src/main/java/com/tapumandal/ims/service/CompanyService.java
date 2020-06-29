package com.tapumandal.ims.service;

import com.tapumandal.ims.entity.Company;
import com.tapumandal.ims.entity.dto.CompanyDto;

public interface CompanyService extends Service<CompanyDto, Company>{
    boolean isCompanyExist(int id);
}
