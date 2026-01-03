package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupUsersRequestDto;
import org.example.incidentmanagement.dto.response.CreateAssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponseDto;



public interface AssigneeGroupUserService {

    
    CreateAssigneeGroupUsersResponseDto addUserInAssigneeGroup (CreateAssigneeGroupUsersRequestDto createAssigneeGroupUsersRequestDto);
    void removeUserFromAssigneeGroup(Integer id);
    
    AssigneeGroupUsersResponseDto findById(Integer id);
    
    
    

}
