package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.Challan;
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
    public int create(Challan challan) {

        getSession().saveOrUpdate(challan);
        getSession().flush();
        getSession().clear();
        return challan.getId();
    }

    @Override
    public int update(Challan challan) {

        Optional<Challan> tmpEntity = Optional.ofNullable(getById(challan.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(challan);
            getSession().flush();
            getSession().clear();
        }
        return challan.getId();
    }

    @Override
    public List<Challan> getAll(Pageable pageable) {


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
        String query = "FROM Challan P WHERE P.companyId = "+ ApplicationPreferences.getUser().getCompany().getId();
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Challan getById(int id) {

        String query = "FROM Challan P WHERE P.id = "+id+" AND P.companyId = "+ApplicationPreferences.getUser().getCompany().getId();
        return (Challan) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Challan> getByKeyAndValue(String key, String value) {
        return (List<Challan>) getSession().createQuery(
                "from Challan where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {
        int companyId = ApplicationPreferences.getUser().getCompany().getId();
        Optional<Challan> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Challan challan = tmpEntity.get();

            getSession().delete(challan);
            return true;
        }else{
            return false;
        }
    }


}