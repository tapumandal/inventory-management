package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.Delivery;
import com.tapumandal.ims.repository.DeliveryRepository;
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
public class DeliveryRepositoryImpl implements DeliveryRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Delivery delivery) {

        getSession().saveOrUpdate(delivery);
        getSession().flush();
        getSession().clear();
        return delivery.getId();
    }

    @Override
    public int update(Delivery delivery) {
        System.out.println("update");
        Optional<Delivery> tmpEntity = Optional.ofNullable(getById(delivery.getId()));
        System.out.println(tmpEntity.get());
        getSession().clear();

        if(tmpEntity.isPresent()) {
            System.out.println("IF");
            getSession().update(delivery);
            getSession().flush();
            getSession().clear();
        }
        System.out.println(delivery.getId());
        return delivery.getId();
    }

    @Override
    public List<Delivery> getAll(Pageable pageable) {


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
        String query = "FROM Delivery P WHERE P.companyId = "+ ApplicationPreferences.getUser().getCompany().getId();
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Delivery getById(int id) {

        String query = "FROM Delivery P WHERE P.id = "+id+" AND P.companyId = "+ApplicationPreferences.getUser().getCompany().getId();
        return (Delivery) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Delivery> getByKeyAndValue(String key, String value) {
        return (List<Delivery>) getSession().createQuery(
                "from Delivery where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {
        int companyId = ApplicationPreferences.getUser().getCompany().getId();
        Optional<Delivery> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Delivery delivery = tmpEntity.get();

            getSession().delete(delivery);
            return true;
        }else{
            return false;
        }
    }


}