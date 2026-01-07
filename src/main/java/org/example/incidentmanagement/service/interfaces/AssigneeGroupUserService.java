package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.createRequest.CrAssigneeGroupUsersRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrAssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponseDto;



public interface AssigneeGroupUserService {

    
    CrAssigneeGroupUsersResponseDto addUserInAssigneeGroup (CrAssigneeGroupUsersRequestDto crAssigneeGroupUsersRequestDto);
    void removeUserFromAssigneeGroup(Integer id);
    AssigneeGroupUsersResponseDto findById(Integer id);
    
    
    

}
