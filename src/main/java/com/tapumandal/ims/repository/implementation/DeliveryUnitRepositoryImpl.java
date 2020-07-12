package com.tapumandal.ims.repository.implementation;

import com.google.gson.Gson;
import com.tapumandal.ims.entity.DeliveryUnit;
import com.tapumandal.ims.repository.DeliveryUnitRepository;
import com.tapumandal.ims.util.ApplicationPreferences;
import com.tapumandal.ims.util.MyPagenation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DeliveryUnitRepositoryImpl implements DeliveryUnitRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(DeliveryUnit deliveryUnit) {

        getSession().saveOrUpdate(deliveryUnit);
        getSession().flush();
        getSession().clear();
        return deliveryUnit.getId();
    }

    @Override
    public int update(DeliveryUnit deliveryUnit) {

        Optional<DeliveryUnit> tmpEntity = Optional.ofNullable(getById(deliveryUnit.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(deliveryUnit);
            getSession().flush();
            getSession().clear();
        }
        return deliveryUnit.getId();
    }

    @Override
    public List<DeliveryUnit> getAll(Pageable pageable) {


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
        String query = "FROM DeliveryUnit P WHERE P.companyId = "+ ApplicationPreferences.getUser().getCompany().getId();
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public DeliveryUnit getById(int id) {

        String query = "FROM DeliveryUnit P WHERE P.id = "+id+" AND P.companyId = "+ApplicationPreferences.getUser().getCompany().getId();
        return (DeliveryUnit) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<DeliveryUnit> getByKeyAndValue(String key, String value) {
        return (List<DeliveryUnit>) getSession().createQuery(
                "from DeliveryUnit where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {
        int companyId = ApplicationPreferences.getUser().getCompany().getId();
        Optional<DeliveryUnit> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            DeliveryUnit deliveryUnit = tmpEntity.get();

            getSession().delete(deliveryUnit);
            return true;
        }else{
            return false;
        }
    }


}