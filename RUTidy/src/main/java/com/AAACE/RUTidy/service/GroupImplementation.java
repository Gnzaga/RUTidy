package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.dto.GroupDTO;
import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.dto.UserInGroupDTO;
import com.AAACE.RUTidy.model.Group;
import com.AAACE.RUTidy.model.User;
import com.AAACE.RUTidy.model.UsersInGroup;
import com.AAACE.RUTidy.repository.GroupRepository;
import com.AAACE.RUTidy.repository.UserRepository;
import com.AAACE.RUTidy.repository.UsersInGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class GroupImplementation implements GroupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UsersInGroupRepository usersInGroupRepository;
    
    public Response createGroup(GroupDTO groupDTO){
        //search for the user in the DB
        Optional<User> optionalUser = userRepository.findByUserID(groupDTO.getOwnerID());
        
        //ensures user exists ( they should, but just in case)
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

        return new Response("group_created", newGroup);

    }

    public List<UsersInGroup> getGroupsIn(int userID){
        List<UsersInGroup> list = usersInGroupRepository.findByUserUserID(userID);
        return list;
    }

    public List<Group> findGroupByName(String name){
        return this.groupRepository.findByName(name);
    }

    public String joinGroup(int groupID, int userID){
        Optional<User> user = this.userRepository.findById(userID);
        if (user.isEmpty()) return "User not found!";

        Optional<Group> group = groupRepository.findByGroupID(groupID);
        if (group.isEmpty()) return "No group found!";

        if (usersInGroupRepository.findByGroupIDAndUserID(groupID, userID) != null){
            return "User already in group!";
        }

        UsersInGroup userInGroup = new UsersInGroup(group.get(), user.get());
        userInGroup.setRoles(0);
        usersInGroupRepository.save(userInGroup);
        return "Success!";

    }

    public void leaveGroup(int UIGroupID){
        usersInGroupRepository.deleteById(UIGroupID);
    }

    public Response updateUserPermission(UserInGroupDTO userInGroupDTO, int role) {
        int userID = userInGroupDTO.getUserID();
        int groupID = userInGroupDTO.getGroupID();
        
        Optional<User> optionalUser = userRepository.findByUserID(userID);
        Optional<Group> optionalGroup = groupRepository.findByID(groupID);
        
        User user = optionalUser.get();
        Group group = optionalGroup.get();
        
        UsersInGroup usersInGroup = new UsersInGroup(group, user);

        usersInGroup.setRoles(role);
        usersInGroupRepository.save(usersInGroup);
    
        return new Response("Permission Updated Successfully", user);
    }
    
}
