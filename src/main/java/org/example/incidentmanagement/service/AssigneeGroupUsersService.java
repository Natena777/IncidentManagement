package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupUsersRequestDto;
import org.example.incidentmanagement.dto.response.CreateAssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponse;



public interface AssigneeGroupUsersService {

    
    CreateAssigneeGroupUsersResponseDto addUserInAssigneeGroup (CreateAssigneeGroupUsersRequestDto createAssigneeGroupUsersRequestDto);
    void removeUserFromAssigneeGroup(Integer id);
    
    AssigneeGroupUsersResponse findById(Integer id);
    
    
    

}
