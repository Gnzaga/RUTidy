package com.AAACE.RUTidy.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AAACE.RUTidy.dto.GroupDTO;
import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.model.Group;
import com.AAACE.RUTidy.model.User;
import com.AAACE.RUTidy.model.UsersInGroup;
import com.AAACE.RUTidy.repository.GroupRepository;
import com.AAACE.RUTidy.repository.UserRepository;
import com.AAACE.RUTidy.repository.UsersInGroupRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UsersInGroupRepository usersGroupRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UsersInGroup> getGroupsIn(int userID){
        List<UsersInGroup> list = this.usersGroupRepository.findByUserID(userID);
        return list;
    }

    public List<Group> findGroupByName(String name){
        return this.groupRepository.findByName(name);
    }

    public String joinGroup(int groupID, int userID){
        Optional<User> user = this.userRepository.findById(userID);
        if (user.isEmpty()) return "User not found!";

        Optional<Group> group = this.groupRepository.findByID(groupID);
        if (group.isEmpty()) return "No group found!";

        if (this.usersGroupRepository.findByGroupIDAndUserID(groupID, userID) != null){
            return "User already in group!";
        }

        UsersInGroup userInGroup = new UsersInGroup(group.get(), user.get());
        userInGroup.setRoles(0);
        this.usersGroupRepository.save(userInGroup);
        return "Success!";

    }

    public void leaveGroup(int UIGroupID){
        this.usersGroupRepository.deleteById(UIGroupID);
    }

    public Response createGroup(GroupDTO groupDTO){
        //search for the user in the DB
        Optional<User> optionalUser = userRepository.findByID(groupDTO.getOwnerID());
        
        //ensures user exists ( they should, but just in case)
        if( optionalUser.isEmpty() ){
            return new Response("User not found", null);
        }
        //create user object
        User owner = optionalUser.get();

        //create group object
        Group newGroup = new Group(groupDTO.getName(),owner);

        //save group to DB
        
        groupRepository.save(newGroup);

        UsersInGroup userInGroup = new UsersInGroup( newGroup, owner );

        //usersGroupRepository.save(userInGroup);

        return new Response("group_created", newGroup);

    }
    
}

