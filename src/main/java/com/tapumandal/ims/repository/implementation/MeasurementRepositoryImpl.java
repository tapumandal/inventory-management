package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.Measurement;
import com.tapumandal.ims.entity.Measurement;
import com.tapumandal.ims.entity.Product;
import com.tapumandal.ims.entity.Supplier;
import com.tapumandal.ims.repository.MeasurementRepository;
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
public class MeasurementRepositoryImpl implements MeasurementRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Measurement measurement) {

        getSession().saveOrUpdate(measurement);
        getSession().flush();
        getSession().clear();
        return measurement.getId();
    }

    @Override
    public int update(Measurement measurement) {

        Optional<Measurement> tmpEntity = Optional.ofNullable(getById(measurement.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(measurement);
            getSession().flush();
            getSession().clear();
        }
        return measurement.getId();
    }

    @Override
    public List<Measurement> getAll(Pageable pageable) {


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
        String query = "FROM Measurement M WHERE M.isDeleted = 0 AND M.companyId = "+ ApplicationPreferences.getUser().getCompany().getId();
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Measurement getById(int id) {

        String query = "FROM Measurement M WHERE M.id = "+id+" AND M.isDeleted = 0 AND M.companyId = "+ ApplicationPreferences.getUser().getCompany().getId();
        return (Measurement) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Measurement> getByKeyAndValue(String key, String value) {
        return (List<Measurement>) getSession().createQuery(
                "from Measurement where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<Measurement> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Measurement measurement = tmpEntity.get();
            measurement.setActive(false);
            measurement.setDeleted(true);
            measurement.setProducts(new ArrayList<Product>());

            update(measurement);
            return true;
        }else{
            return false;
        }
    }


}