package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.ReceiveChallan;
import com.tapumandal.ims.repository.ChallanManagementRepository;
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
public class ChallanManagementRepositoryImpl implements ChallanManagementRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(ReceiveChallan receiveChallan) {

        getSession().saveOrUpdate(receiveChallan);
        getSession().flush();
        getSession().clear();
        return receiveChallan.getId();
    }

    @Override
    public int update(ReceiveChallan receiveChallan) {

        Optional<ReceiveChallan> tmpEntity = Optional.ofNullable(getById(receiveChallan.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(receiveChallan);
            getSession().flush();
            getSession().clear();
        }
        return receiveChallan.getId();
    }

    @Override
    public List<ReceiveChallan> getAll(Pageable pageable) {


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
        String query = "FROM ReceiveChallan P WHERE P.companyId = "+ ApplicationPreferences.getUser().getCompany().getId();
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public ReceiveChallan getById(int id) {

        String query = "FROM ReceiveChallan P WHERE P.id = "+id+" AND P.companyId = "+ApplicationPreferences.getUser().getCompany().getId();
        return (ReceiveChallan) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<ReceiveChallan> getByKeyAndValue(String key, String value) {
        return (List<ReceiveChallan>) getSession().createQuery(
                "from ReceiveChallan where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {
        int companyId = ApplicationPreferences.getUser().getCompany().getId();
        Optional<ReceiveChallan> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            ReceiveChallan receiveChallan = tmpEntity.get();

            getSession().delete(receiveChallan);
            return true;
        }else{
            return false;
        }
    }


}