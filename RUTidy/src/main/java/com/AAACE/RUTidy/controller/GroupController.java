package com.AAACE.RUTidy.controller;

import java.util.List;

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
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService service;

    @GetMapping("/name")
    public ResponseEntity<List<Group>> getGroupsByName(@RequestParam String groupName){
        List<Group> groups = this.service.findGroupByName(groupName);
        if (groups.size() == 0 || groups == null){
            return new ResponseEntity<List<Group>>(groups, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Group>>(groups, HttpStatus.ACCEPTED);
    }

    

    @GetMapping("/in")
    public ResponseEntity<List<UsersInGroup>> getGroupsIn(@RequestParam int userID){
        List<UsersInGroup> list = this.service.getGroupsIn(userID);
        if (list.size() == 0 || list == null){
            return new ResponseEntity<List<UsersInGroup>>(list, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<UsersInGroup>>(list, HttpStatus.ACCEPTED);
    }

    @PutMapping("/join")
    public ResponseEntity<String> joinGroup(@RequestParam int groupID, @RequestParam int userID){
        String message = this.service.joinGroup(groupID, userID);
        return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/leave")
    public void leaveGroup(@RequestParam int UIGroupID){
        this.service.leaveGroup(UIGroupID);
    }

}
