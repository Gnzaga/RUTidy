package com.AAACE.RUTidy.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AAACE.RUTidy.dto.GroupDTO;
import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.dto.UserInGroupDTO;
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
        return list;
    }

    public List<Group> getJoinedGroups(int userID){
        List<UsersInGroup> list = this.usersGroupRepository.findByUserUserID(userID);
        List<Group> groups = list.stream().map(UsersInGroup::getGroup).collect(Collectors.toList());
        return groups;
    }

    public List<Group> findGroupByName(String name){
        return this.groupRepository.findByName(name);
    }

    public Optional<Group> findByGroupID(int groupID){
        return this.groupRepository.findByGroupID(groupID);
    }

    public List<User> findUsersInGroup(int groupID){
        List<UsersInGroup> usersInGroup = this.usersGroupRepository.findByGroupGroupID(groupID);

     return usersInGroup.stream().map(UsersInGroup::getUser).collect(Collectors.toList());
    }

    public List<UsersInGroup> getUsersInGroupObject(int groupID){
        List<UsersInGroup> usersInGroup = this.usersGroupRepository.findByGroupGroupID(groupID);
        return usersInGroup;
    }

    public Response addUserToGroup(int groupID, String textEntry){
        Optional<User> userByEmail = this.userRepository.findByEmail(textEntry);
        Optional<User> userByUsername = this.userRepository.findByUsername(textEntry);

        if (userByEmail.isEmpty() && userByUsername.isEmpty()){
            return new Response("User not found!", null);
        }

        User user = userByEmail.isEmpty() ? userByUsername.get() : userByEmail.get();

        Optional<Group> group = this.groupRepository.findByGroupID(groupID);

        if (group.isEmpty()){
            return new Response("Group not found!", null);
        }

        if (!this.usersGroupRepository.findByGroupGroupIDAndUserUserID(groupID, user.getUserID()).isEmpty()){
            return new Response("User already in group!", null);
        }

        UsersInGroup userInGroup = new UsersInGroup(group.get(), user);
        userInGroup.setRoles(2);
        this.usersGroupRepository.save(userInGroup);

        return new Response("Success!", userInGroup);
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
        Optional <UsersInGroup> userInGroup = this.usersGroupRepository.findById(UIGroupID);

        if (userInGroup.isEmpty()) return;
        
        this.usersGroupRepository.delete(userInGroup.get());
        }

    public void removeUserFromGroup(int userID, int groupID){
        UsersInGroup userInGroup = this.usersGroupRepository.findByGroupGroupIDAndUserUserID(groupID, userID).get();
        if (userInGroup == null) return;
        this.usersGroupRepository.delete(userInGroup);
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


    public Response updateUserPermission(int userID, int groupID, int role) {
      
        Optional<UsersInGroup> optionalUsersInGroup = usersGroupRepository.findByGroupGroupIDAndUserUserID(groupID, userID);
        if(optionalUsersInGroup.isEmpty()){
            return new Response("User not found in group", null);
        }

        UsersInGroup usersInGroup = optionalUsersInGroup.get();

        usersInGroup.setRoles(role);
        this.usersGroupRepository.save(usersInGroup);
    
        return new Response("Permission Updated Successfully", usersInGroup);
    }


    
}

