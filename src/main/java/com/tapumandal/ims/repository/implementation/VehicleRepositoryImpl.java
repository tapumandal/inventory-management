package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.Measurement;
import com.tapumandal.ims.entity.Vehicle;
import com.tapumandal.ims.repository.VehicleRepository;
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
public class VehicleRepositoryImpl implements VehicleRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Vehicle vehicle) {

        getSession().saveOrUpdate(vehicle);
        getSession().flush();
        getSession().clear();
        return vehicle.getId();
    }

    @Override
    public int update(Vehicle vehicle) {

        Optional<Vehicle> tmpEntity = Optional.ofNullable(getById(vehicle.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(vehicle);
            getSession().flush();
            getSession().clear();
        }
        return vehicle.getId();
    }

    @Override
    public List<Vehicle> getAll(Pageable pageable) {


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
        String query = "FROM Vehicle P WHERE P.isDeleted = 0 AND P.companyId = "+ ApplicationPreferences.getUser().getCompany().getId();
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Vehicle getById(int id) {

        String query = "FROM Vehicle P WHERE P.id = "+id+" AND P.isDeleted = 0 AND P.companyId = "+ApplicationPreferences.getUser().getCompany().getId();
        return (Vehicle) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Vehicle> getByKeyAndValue(String key, String value) {
        return (List<Vehicle>) getSession().createQuery(
                "from Vehicle where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<Vehicle> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Vehicle vehicle = tmpEntity.get();
            vehicle.setActive(false);
            vehicle.setDeleted(true);
            update(vehicle);
            return true;
        }else{
            return false;
        }
    }


}