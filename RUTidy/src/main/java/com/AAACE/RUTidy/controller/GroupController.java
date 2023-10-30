package com.AAACE.RUTidy.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AAACE.RUTidy.model.*;
import com.AAACE.RUTidy.dto.*;
import com.AAACE.RUTidy.service.*;

@RestController
@CrossOrigin
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService service;

    @Autowired
    private UserService userService;

    @Autowired
    private UsersInGroupService usersInGroupService;

    /* to be used when invitations are implemented properly
    @Autowired
    private InvitationService invitationService;
    */

// EXAMPLE
// /group/name?groupName={groupName}
    @GetMapping("/name")
    public ResponseEntity<List<Group>> getGroupsByName(@RequestParam String groupName){
        List<Group> groups = this.service.findGroupByName(groupName);
        if (groups.size() == 0 || groups == null){
            return new ResponseEntity<List<Group>>(groups, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Group>>(groups, HttpStatus.ACCEPTED);
    }
    
// EXAMPLE
// /group/in?userID={userID}
    @GetMapping("/in")
    public ResponseEntity<List<UsersInGroup>> getGroupsIn(@RequestParam int userID){
        List<UsersInGroup> list = this.service.getGroupsIn(userID);
        if (list.size() == 0 || list == null){
            return new ResponseEntity<List<UsersInGroup>>(list, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<UsersInGroup>>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/joined-groups")
    public ResponseEntity<List<Group>> getJoinedGroups(@RequestParam int userID){
        List<Group> list = this.service.getJoinedGroups(userID);
        if (list.size() == 0 || list == null){
            return new ResponseEntity<List<Group>>(list, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Group>>(list, HttpStatus.ACCEPTED);
    }

    //gives all the groups you OWN
    @GetMapping(path = "/myGroups")
    public ResponseEntity<List<Group>> getMyGroups(@RequestParam int userID) {
        User user = userService.getUser(userID);
        List<UsersInGroup> groupsIn = this.service.getGroupsIn(userID);
        if (user == null || groupsIn == null || groupsIn.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<Group> myGroups = new ArrayList<Group>();

        for (UsersInGroup groupIn : groupsIn) {
            if (groupIn.getGroup().getOwner() == user) {
                myGroups.add(groupIn.getGroup());
            }
        }
        return new ResponseEntity<List<Group>>(myGroups, HttpStatus.ACCEPTED);
    }

// EXAMPLE
// http://localhost:8080/group/join?groupID={GROUPID}&userID={USERID}
    @PutMapping("/join")
    public ResponseEntity<String> joinGroup(@RequestParam int groupID, @RequestParam int userID){
        String message = this.service.joinGroup(groupID, userID);
        return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
    }

// EXAMPLE
// http://localhost:8080/group/leave?UIGroupID={UIGROUPID}
    @DeleteMapping("/leave")
    public Response leaveGroup(@RequestParam int UIGroupID){
        try{
            this.service.leaveGroup(UIGroupID);
            return new Response("Success!", null);
        } 
        catch (Exception e){
            return new Response("Error!", null);
        }
    }

// EXAMPLE
// http://localhost:8080/group/listUsersInGroup?groupID={GROUPID}
    @GetMapping("/listUsersInGroup")
    public ResponseEntity<List<User>> getUsersInGroup(@RequestParam int groupID){
        List<User> list = this.service.findUsersInGroup(groupID);

        if (list.size() == 0 || list == null){
            return new ResponseEntity<List<User>>(list, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<User>>(list, HttpStatus.ACCEPTED);
    }

    /* EXAMPLE:
    {
        "name" : "{GroupName}",
        "ownerID" : {OwnerID}
    }
    */
    @PostMapping(path = "/create")
    public Response createGroup(@RequestBody GroupDTO groupDTO) {
        return service.createGroup(groupDTO);
    }  

    /* EXAMPLE:
    {
        "userID" : {UserID},
        "groupID" : {GroupID}
    }
    */
    @PostMapping(path = "/addToGroup")
    public Response addUserToGroup(@RequestBody UserInGroupDTO userInGroupDTO) {
        return usersInGroupService.addUserToGroup(userInGroupDTO);
    }

}