package com.tapumandal.ims.repository;

import com.tapumandal.ims.entity.User;

public interface UserRepository extends Repository<User>{

    public boolean isUserExist(String userName);
    public User getUserByUserName(String username);

}
