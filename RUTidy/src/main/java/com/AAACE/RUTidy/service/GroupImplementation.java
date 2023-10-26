package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.dto.GroupDTO;
import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.model.Group;
import com.AAACE.RUTidy.model.User;
import com.AAACE.RUTidy.repository.GroupRepository;
import com.AAACE.RUTidy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GroupImplementation implements GroupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;
    
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

}
