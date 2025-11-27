package org.example.incidentmanagement.service;

import org.example.incidentmanagement.model.User;

public interface LoginService {

    User login(String username, String password);

}
