package com.AAACE.RUTidy.service.group;

import java.util.Optional;
import java.util.HashMap;
import java.util.List;

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

    /**
     * Returns a users role in a group
     * 
     * @param userID
     * @param groupID
     * @return
     */
    
    public Response getUsersRoleInGroup(int userID, int groupID){
        Optional<UsersInGroup> optionalUsersInGroup = usersInGroupRepository.findByGroupGroupIDAndUserUserID(groupID, userID);

        if(optionalUsersInGroup.isEmpty()){
            return new Response("User not found in group", null);
        }

        UsersInGroup usersInGroup = optionalUsersInGroup.get();

        return new Response("success", usersInGroup.getRoles());
    }
    
    /**
     * Returns a hashmap of all userID : role in group
     * @param groupID
     * @return
     */
    public HashMap<Integer, Integer> getAllUsersRoleInGroup(int groupID){
        List<UsersInGroup> usersInGroup = usersInGroupRepository.findByGroupGroupID(groupID);

        if(usersInGroup.isEmpty()){
            return null;
        }

        HashMap<Integer, Integer> usersInGroupMap = new HashMap<Integer, Integer>();

        for(UsersInGroup userInGroup : usersInGroup){
            usersInGroupMap.put(userInGroup.getUser().getUserID(), userInGroup.getRoles());
        }

        return usersInGroupMap;
    }
}
