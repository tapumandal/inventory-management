package com.tapumandal.ims.repository;

import com.tapumandal.ims.entity.Company;

public interface CompanyRepository extends Repository<Company>{

    public Company getCompanyFirstTime(int companyId);
}
