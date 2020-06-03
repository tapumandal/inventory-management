package com.tapumandal.ims.service;

import com.tapumandal.ims.entity.User;

public interface UserService extends Service<User>{

    public boolean isUserExist(String userName);
    public User createUser(User user);
}
