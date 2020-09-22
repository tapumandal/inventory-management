package com.tapumandal.ims.util;

import com.google.gson.Gson;
import com.tapumandal.ims.entity.Company;
import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.prefs.Preferences;


@Component
public class ApplicationPreferences {

    @Autowired
    UserService userService;

    private static Preferences preferences;

    private static User user;

    public void saveUserByUsername(String username) {
        User u = userService.getByValue("username", username);
        preferences = Preferences.userRoot().node(this.getClass().getName());
        preferences.put("name", u.getName());
        preferences.put("phone", u.getPhone());
        preferences.put("role", u.getRole());
        preferences.put("companyId", String.valueOf(u.getCompany().getId()));

    }

    public static User getUser(){
        user = new User();
        preferences = Preferences.userRoot().node(ApplicationPreferences.class.getName());
        user.setName(preferences.get("name", null));
        user.setPhone(preferences.get("phone", null));
        user.setRole(preferences.get("role", null));
        Company com = new Company();
        com.setId(Integer.parseInt(preferences.get("companyId", "0")));
        user.setCompany(com);
        return user;
    }
}
