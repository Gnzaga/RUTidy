package com.AAACE.RUTidy.controller;

import com.AAACE.RUTidy.dto.*;
import com.AAACE.RUTidy.service.*;
import com.AAACE.RUTidy.model.*;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AAACE.RUTidy.model.Group;
import com.AAACE.RUTidy.model.UsersInGroup;
import com.AAACE.RUTidy.service.GroupService;




@RestController
@CrossOrigin
@RequestMapping(value = "/group", method={RequestMethod.GET , RequestMethod.POST} )
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UsersInGroupService usersInGroupService;

    @PostMapping(path = "/create")
    public Response createGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.createGroup(groupDTO);
    }  

    @PostMapping(path = "/addToGroup")
    public Response addUserToGroup(@RequestBody UserInGroupDTO userInGroupDTO) {
        return usersInGroupService.addUserToGroup(userInGroupDTO);
    }
    
    @GetMapping("/name")
    public ResponseEntity<List<Group>> getGroupsByName(@RequestParam String groupName){
        List<Group> groups = groupService.findGroupByName(groupName);
        if (groups.size() == 0 || groups == null){
            return new ResponseEntity<List<Group>>(groups, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Group>>(groups, HttpStatus.ACCEPTED);
    }

    @GetMapping("/in")
    public ResponseEntity<List<UsersInGroup>> getGroupsIn(@RequestParam int userID){
        List<UsersInGroup> list = groupService.getGroupsIn(userID);
        if (list.size() == 0 || list == null){
            return new ResponseEntity<List<UsersInGroup>>(list, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<UsersInGroup>>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/usersIn")
    public ResponseEntity<List<UsersInGroup>> getUsersIn(@RequestParam int groupID){
        List<UsersInGroup> list = groupService.getUsersIn(groupID);
        if (list.size() == 0 || list == null){
            return new ResponseEntity<List<UsersInGroup>>(list, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<UsersInGroup>>(list, HttpStatus.ACCEPTED);
    }

    @PutMapping("/join")
    public ResponseEntity<String> joinGroup(@RequestParam int groupID, @RequestParam int userID){
        String message = groupService.joinGroup(groupID, userID);
        return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/leave")
    public void leaveGroup(@RequestParam int UIGroupID){
        groupService.leaveGroup(UIGroupID);
    }

    @PostMapping(path = "/updateUserPermission")
    public Response updateUserPermission(@RequestBody UserInGroupDTO userInGroupDTO, @RequestParam int roles){
        return groupService.updateUserPermission(userInGroupDTO, roles);
    }


   

/** 
    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.login(loginDTO);
        return loginResponse;
    }

    */


    

}
