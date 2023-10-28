package com.AAACE.RUTidy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AAACE.RUTidy.dto.*;
import com.AAACE.RUTidy.repository.*;
import com.AAACE.RUTidy.model.*;


@Service
public class UsersInGroupImplementation implements UsersInGroupService{

    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UsersInGroupRepository usersInGroupRepository;

    /**
     * Adds a user to a group
     * @param userDTO
     * @param groupDTO
     * 
     */
    public Response addUserToGroup(UserInGroupDTO userInGroupDTO){
        int userID = userInGroupDTO.getUserID();
        int groupID = userInGroupDTO.getGroupID();
        //check if user and group exist
        Optional<User> optionalUser = userRepository.findByUserID(userID);
        Optional<Group> optionalGroup = groupRepository.findByGroupID(groupID);

        if(optionalUser.isEmpty()){
            return new Response("User not found", null);
        }
        User user = optionalUser.get();

        if(optionalGroup.isEmpty()){
            return new Response("Group not found", null);
        }

        Group group = optionalGroup.get();

        //check if user is already in group
        Optional<UsersInGroup> optionalUsersInGroup = usersInGroupRepository.findByGroupGroupIDAndUserUserID(group.getGroupID(), user.getUserID());

        if(optionalUsersInGroup.isPresent()){
            return new Response("User already in group", null);
        }

        //add user to group
        groupRepository.save(group);

        UsersInGroup userInGroup = new UsersInGroup(group, user);
    
        usersInGroupRepository.save(userInGroup);

        return new Response("User added to group", userInGroup);
    }

}
