package com.tapumandal.ims.service.implementation;

import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.repository.UserRepository;
import com.tapumandal.ims.service.UserService;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    User user;


    @Override
    public User createUser(User user) {
        try {
            if(userRepository.create(user).getId() > 0){
                return getById(user.getId());
            }else{
                return null;
            }

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User create(User activeService) {
        return null;
    }

    @Override
    public User update(User o) {
        return null;
    }

    @Override
    public List getAll(Pageable pageable) {

        return userRepository.getAll(pageable);
    }

    @Override
    public User getById(int id) {

        return userRepository.getById(id);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public User getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<User> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return false;
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return null;
    }


    public boolean isUserExist(String userName){
        return userRepository.isUserExist(userName);
    }

}
