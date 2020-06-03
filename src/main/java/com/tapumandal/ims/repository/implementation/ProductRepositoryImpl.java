package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.Product;
import com.tapumandal.ims.repository.ProductRepository;
import com.tapumandal.ims.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

//    @Autowired
//    SessionFactory session;

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public Product create(Product product) {

        getSession().saveOrUpdate(product);
        return product;
    }

    @Override
    public Product update(Product product) {

        getSession().update(product);
        return getById(product.getId());
    }

    @Override
    public List<Product> getAll() {
        String query = "FROM Product P WHERE P.isDeleted = 0";
        return getSession().createQuery(query).list();
    }

    @Override
    public Product getById(int id) {

        String query = "FROM Product P WHERE P.id = "+id+" AND P.isDeleted = 0";
        return (Product) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Product> getByKeyAndValue(String key, String value) {
        return (List<Product>) getSession().createQuery(
                "from Product where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

}