package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.Measurement;
import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.entity.User;

import com.tapumandal.ims.repository.UserRepository;
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
public class UserRepositoryImpl  implements UserRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {

        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(User user) {

        getSession().save(user);
        getSession().flush();
        getSession().clear();
        return user.getId();
    }

    @Override
    public int update(User user) {

        Optional<User> tmpEntity = Optional.ofNullable(getById(user.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()){
            getSession().update(user);
            getSession().flush();
            getSession().clear();
            return user.getId();
        }else{
            return 0;
        }
    }

    @Override
    public List<User> getAll(Pageable pageable) {

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


    public User getByUserName(String userName) {

        String query = "FROM User U WHERE U.username = '"+userName+"' AND U.isDeleted = 0";
        return (User) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<User> getByKeyAndValue(String key, String value) {
        return (List<User>) getSession().createQuery(
                "from User where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<User> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            User user = tmpEntity.get();
            user.setActive(false);
            user.setDeleted(true);
            update(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isUserExist(String userName) {

        Optional<User> tmpEntity = Optional.ofNullable(getByUserName(userName));
        if(tmpEntity.isPresent()){
            return true;
        }

        return false;

    }


    private Query getQuery(){
        String query = "FROM User U WHERE U.isDeleted = 0 AND U.company.id = "+ApplicationPreferences.getUser().getCompany().getId();
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public User getById(int id) {

        String query = "FROM User U WHERE U.id = "+id+" AND U.isDeleted = 0 AND U.company.id = "+ApplicationPreferences.getUser().getCompany().getId();
        return (User) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public User getUserByUserName(String username) {
        return (User) getSession().createQuery(
                "from User where username = :value"
        ).setParameter("value", username).getSingleResult();
    }
}