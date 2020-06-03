package com.tapumandal.ims.service.implementation;

import com.tapumandal.ims.entity.Company;
import com.tapumandal.ims.entity.Product;
import com.tapumandal.ims.repository.CompanyRepository;
import com.tapumandal.ims.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    Company company;

    @Override
    public List<Company> create(Company company) {

//        try {
//            if(companyRepository.create(company)){
//                return company;
//            }
//        }catch (Exception e){
//            return null;
//        }
        return null;
    }

    @Override
    public Company update(Company company) {
        return null;
    }

    @Override
    public ArrayList<Company> getAll() {
        return null;
    }

    @Override
    public Company getById(int id) {
        return null;
    }

    @Override
    public Company getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Company> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return false;
    }
}
