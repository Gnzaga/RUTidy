package com.AAACE.RUTidy.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<UsersInGroup> list = this.usersGroupRepository.findByUserUserID(userID);
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getGroup().getName());
        }
        return list;
    }

    public List<Group> findGroupByName(String name){
        return this.groupRepository.findByName(name);
    }

    public List<User> findUsersInGroup(int groupID){
        List<UsersInGroup> usersInGroup = this.usersGroupRepository.findByGroupGroupID(groupID);

     return usersInGroup.stream().map(UsersInGroup::getUser).collect(Collectors.toList());
 
    }

    public List<UsersInGroup> findUIGInGroup(int groupID){
        List<UsersInGroup> usersInGroup = this.usersGroupRepository.findByGroupGroupID(groupID);
        return usersInGroup;
 
    }

    public String joinGroup(int groupID, int userID){
        Optional<User> user = this.userRepository.findById(userID);
        if (user.isEmpty()) return "User not found!";

        Optional<Group> group = this.groupRepository.findByGroupID(groupID);
        if (group.isEmpty()) return "No group found!";

        if (this.usersGroupRepository.findByGroupGroupIDAndUserUserID(groupID, userID) != null){
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
        System.out.println(groupDTO.getGroupID());
        System.out.println(groupDTO.getName());
        System.out.println(groupDTO.getOwnerID());

        Optional<User> optionalUser = userRepository.findByUserID(groupDTO.getOwnerID());
        
        //ensures user exists ( they should, but just in case)
        System.out.println(optionalUser);
        if( optionalUser.isEmpty() ){
            return new Response("User not found", null);
        }
        //create user object
        User owner = optionalUser.get();

        //create group object
        Group newGroup = new Group(
            groupDTO.getName(),
            owner
        );

        //save group to DB
        groupRepository.save(newGroup);
        UsersInGroup temp = new UsersInGroup(newGroup,owner);
        usersGroupRepository.save(temp);
        return new Response("group_created", newGroup);

    }

    public Response updateUserPermission(int groupID, int userID, int role) {
        
        Optional<User> optionalUser = userRepository.findByUserID(userID);
        if( optionalUser.isEmpty() ){
            return new Response("User not found", null);
        }

        Optional<Group> optionalGroup = groupRepository.findByGroupID(groupID);
        if( optionalGroup.isEmpty() ){
            return new Response("Group not found", null);
        }

        User user = optionalUser.get();
        Group group = optionalGroup.get();

        Optional<UsersInGroup> optionalUserInGroup = usersGroupRepository.findByGroupGroupIDAndUserUserID(groupID, userID);
        if( optionalUserInGroup.isEmpty() ){
            return new Response("User in group not found", null);
        }
        
        UsersInGroup usersInGroup = optionalUserInGroup.get();

        usersInGroup.setRoles(role);
        usersGroupRepository.save(usersInGroup);
    
        return new Response("Permission Updated Successfully", user);
    }


    
}

