package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.Company;
import com.tapumandal.ims.entity.Product;
import com.tapumandal.ims.repository.CompanyRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    Company company;

    @Override
    public Session getSession() {

        return entityManager.unwrap(Session.class);
    }

    @Override
    public Company create(Company company) {

        getSession().saveOrUpdate(company);
        return company;
    }

    @Override
    public Company update(Company o) {
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
    public ArrayList<Company> getByKeyAndValue(String key, String value) {
        return (ArrayList<Company>) getSession().createQuery(
                "from Company where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

}