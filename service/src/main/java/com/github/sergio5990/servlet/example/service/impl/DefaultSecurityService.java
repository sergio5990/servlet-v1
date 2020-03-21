package com.github.sergio5990.servlet.example.service.impl;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.impl.DefaultAuthUserDao;
import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.service.SecurityService;

public class DefaultSecurityService implements SecurityService {
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();

    private static volatile SecurityService instance;

    public static SecurityService getInstance() {
        SecurityService localInstance = instance;
        if (localInstance == null) {
            synchronized (SecurityService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultSecurityService();
                }
            }
        }
        return localInstance;
    }

    public AuthUser login(String login, String password) {
        AuthUser user = authUserDao.getByLogin(login);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
