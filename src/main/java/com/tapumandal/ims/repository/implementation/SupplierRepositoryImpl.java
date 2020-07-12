package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.Supplier;
import com.tapumandal.ims.repository.SupplierRepository;
import com.tapumandal.ims.repository.WarehouseRepository;
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
public class SupplierRepositoryImpl implements SupplierRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Supplier supplier) {
        getSession().saveOrUpdate(supplier);
        getSession().flush();
        getSession().clear();
        return supplier.getId();
    }

    @Override
    public int update(Supplier supplier) {
        Optional<Supplier> tmpEntity = Optional.ofNullable(getById(supplier.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(supplier);
            getSession().flush();
            getSession().clear();
        }
        return supplier.getId();
    }

    @Override
    public List<Supplier> getAll(Pageable pageable) {


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
        String query = "FROM Supplier M WHERE M.isDeleted = 0 AND M.companyId = "+ companyId();
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Supplier getById(int id) {

        String query = "FROM Supplier M WHERE M.id = "+id+" AND M.isDeleted = 0 AND M.companyId = "+ companyId();
        return (Supplier) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Supplier> getByKeyAndValue(String key, String value) {
        return (List<Supplier>) getSession().createQuery(
                "from Supplier where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<Supplier> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Supplier supplier = tmpEntity.get();
            supplier.setActive(false);
            supplier.setDeleted(true);
            update(supplier);
            return true;
        }else{
            return false;
        }
    }

    private int companyId(){
        return ApplicationPreferences.getUser().getCompany().getId();
    }


}