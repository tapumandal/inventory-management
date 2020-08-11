package com.tapumandal.ims.service.implementation;

import com.tapumandal.ims.entity.Company;
import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.entity.dto.CompanyDto;
import com.tapumandal.ims.repository.CompanyRepository;
import com.tapumandal.ims.service.CompanyService;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    private Company company;

    public CompanyServiceImpl(){}

    public CompanyServiceImpl(Company company){
        this.company = company;
    }

    @Override
    public Company create(CompanyDto companyDto) {

        Company pro = new Company(companyDto);
        Optional<Company> company;

//        try{
            int companyId = companyRepository.create(pro);
            company = Optional.ofNullable(companyRepository.getCompanyFirstTime(companyId));
//        }catch (Exception e){
//            return null;
//        }

        if(company.isPresent()){
            return company.get();
        }else{
            return null;
        }
    }

    @Override
    public Company update(CompanyDto companyDto) {


        Company pro = new Company(companyDto);

        Optional<Company> company;
        try{
            int proId = companyRepository.update(pro);
            company = Optional.ofNullable(companyRepository.getById(proId));
        }catch (Exception e){
            return null;
        }

        if(company.isPresent()){
            return company.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Company> getAll(Pageable pageable) {
        Optional<List<Company>> companys = Optional.ofNullable(companyRepository.getAll(pageable));

        if(companys.isPresent()){
            return companys.get();
        }else{
            return null;
        }
    }

    @Override
    public Company getById(int id) {

        Optional<Company> company = Optional.ofNullable(companyRepository.getById(id));

        if(company.isPresent()){
            return company.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return companyRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
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
        Optional<Company> company = Optional.ofNullable(companyRepository.getById(id));
        if(company.isPresent()){
            if(company.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return company.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return companyRepository.getPageable(pageable);
    }

    @Override
    public boolean isCompanyExist(int id) {

        Optional<Company> company = Optional.ofNullable(companyRepository.getById(id));
        if(company.isPresent()){
            return true;
        }
        return false;
    }


}
