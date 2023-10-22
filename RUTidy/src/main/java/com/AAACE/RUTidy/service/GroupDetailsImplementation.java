package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.dto.GroupDTO;
import com.AAACE.RUTidy.dto.UserDTO;
import com.AAACE.RUTidy.dto.UpdateUserPermission;
import com.AAACE.RUTidy.model.Group;
import com.AAACE.RUTidy.model.User;
import com.AAACE.RUTidy.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class GroupDetailsImplementation implements GroupDetailsService{
    
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UpdateUserPermission grantMember(GroupDTO groupDTO, UserDTO userDTO){
        User user = new User(
            userDTO.getName(),
            userDTO.getEmail(),
            this.passwordEncoder.encode(userDTO.getPassword()),
            userDTO.getUsername()
        );
        
        Group group = new Group(
            groupDTO.getName(),
            groupDTO.getMembers(),
            groupDTO.getOwner(),
            groupDTO.getGroupID(),
            groupDTO.getPendingMembers(),
            groupDTO.getSentInvitations(),
            groupDTO.getPermissionLevels()
        );
        groupRepository.save(group);
        return new UpdateUserPermission("Permission Updated", group, user);

    }

    public UpdateUserPermission grantManage(GroupDTO groupDTO, UserDTO userDTO){
        User user = new User(
            userDTO.getName(),
            userDTO.getEmail(),
            this.passwordEncoder.encode(userDTO.getPassword()),
            userDTO.getUsername()
        );

        Group group = new Group(
            groupDTO.getName(),
            groupDTO.getMembers(),
            groupDTO.getOwner(),
            groupDTO.getGroupID(),
            groupDTO.getPendingMembers(),
            groupDTO.getSentInvitations(),
            groupDTO.getPermissionLevels()
        );
        groupRepository.save(group);
        return new UpdateUserPermission("Permission Updated", group, user);

    }

    public UpdateUserPermission grantAdmin(GroupDTO groupDTO, UserDTO userDTO){
        User user = new User(
            userDTO.getName(),
            userDTO.getEmail(),
            this.passwordEncoder.encode(userDTO.getPassword()),
            userDTO.getUsername()
        );

        Group group = new Group(
            groupDTO.getName(),
            groupDTO.getMembers(),
            groupDTO.getOwner(),
            groupDTO.getGroupID(),
            groupDTO.getPendingMembers(),
            groupDTO.getSentInvitations(),
            groupDTO.getPermissionLevels()
        );
        groupRepository.save(group);
        return new UpdateUserPermission("Permission Updated", group, user);

    }

}
