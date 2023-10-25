package com.AAACE.RUTidy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Group> findGroupByName(String name){
        return this.groupRepository.findByName(name);
    }

    public String joinGroup(int groupID, int userID){
        Optional<User> user = this.userRepository.findById(userID);
        if (user.isEmpty()) return "User not found!";

        Optional<Group> group = this.groupRepository.findByGroupID(groupID);
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
    
}
