package com.tapumandal.ims.service;

import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.entity.dto.UserDto;

public interface UserService extends Service<UserDto, User>{

    public boolean isUserExist(String userName);
    public User createUser(UserDto userDto);
    public User getUserByUserName(String username);

}
