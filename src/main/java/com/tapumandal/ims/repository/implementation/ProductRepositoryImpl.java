package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.Measurement;
import com.tapumandal.ims.entity.Product;
import com.tapumandal.ims.repository.ProductRepository;
import com.tapumandal.ims.util.ApplicationPreferences;
import com.tapumandal.ims.util.MyPagenation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Product product) {

        getSession().saveOrUpdate(product);
        getSession().flush();
        getSession().clear();
        return product.getId();
    }

    @Override
    public int update(Product product) {

        getSession().update(product);
        getSession().flush();
        getSession().clear();
        return product.getId();
    }

    @Override
    public List<Product> getAll(Pageable pageable) {


        Query resQuery = getQuery();

        int pageNum = pageable.getPageNumber();
        if(pageNum<1){
            pageNum = 1;
        }

        resQuery.setFirstResult((pageNum-1)*pageable.getPageSize());
        resQuery.setMaxResults(pageable.getPageSize());
        return resQuery.getResultList();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        Query resQuery = getQuery();

        MyPagenation myPagenation = new MyPagenation();

        myPagenation.setTotalElement(resQuery.getResultList().size());
        return myPagenation;
    }

    private Query getQuery(){
        String query = "FROM Product P WHERE P.isDeleted = 0 AND P.companyId = "+ ApplicationPreferences.getUser().getCompany().getId();
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Product getById(int id) {

        String query = "FROM Product P WHERE P.id = "+id+" AND P.isDeleted = 0 AND P.companyId = "+ApplicationPreferences.getUser().getCompany().getId();
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

        Optional<Product> proTmp = Optional.ofNullable(getById(id));
        if(proTmp.isPresent()){
            Product product = proTmp.get();
            product.setActive(false);
            product.setDeleted(true);
            product.setMeasurement(new ArrayList<Measurement>());

            update(product);
            return true;
        }else{
            return false;
        }
    }


}