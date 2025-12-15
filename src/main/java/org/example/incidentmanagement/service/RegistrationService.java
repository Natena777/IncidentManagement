package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.requests.RegistrationUserDto;
import org.example.incidentmanagement.dto.response.RegistrationUserRespDto;
import org.example.incidentmanagement.entity.User;

public interface RegistrationService {

    RegistrationUserRespDto registerUser(RegistrationUserDto userDto);


}
