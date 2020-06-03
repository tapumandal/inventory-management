package com.tapumandal.ims.repository.implementation;

import com.tapumandal.ims.entity.User;

import com.tapumandal.ims.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

//    @Autowired
//    SessionFactory session;

    @Autowired
    EntityManager entityManager;

    @Autowired
    User user;

    @Override
    public Session getSession() {

        return entityManager.unwrap(Session.class);
    }


    @Override
    public User create(User user) {

        getSession().save(user);

        return user;
    }

    @Override
    public User update(User o) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return getSession().createQuery("from User").list();
    }

    @Override
    public User getById(int id) {

        return getSession().load(User.class, id);
    }

    @Override
    public ArrayList<User> getByKeyAndValue(String key, String value) {
        return (ArrayList<User>) getSession().createQuery(
                "from User where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


    @Override
    public boolean isUserExist(String userName) {

        if(getByKeyAndValue("email", userName).isEmpty() || getByKeyAndValue("email", userName) == null){
            return false;
        }else{
            return true;
        }
    }
}