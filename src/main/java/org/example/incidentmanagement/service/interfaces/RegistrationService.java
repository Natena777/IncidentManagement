package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.requests.RegistrationUserDto;
import org.example.incidentmanagement.dto.response.RegistrationUserRespDto;

public interface RegistrationService {

    RegistrationUserRespDto registerUser(RegistrationUserDto userDto);


}
