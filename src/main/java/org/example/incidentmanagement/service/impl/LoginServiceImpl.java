package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.model.User;
import org.example.incidentmanagement.service.LoginService;

public class LoginServiceImpl implements LoginService {


    @Override
    public User login(String username, String password) {
        System.out.println("Login");
        return null;
    }
}
