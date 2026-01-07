package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.entity.User;

public interface LoginService {

    User login(String username, String password);

}
