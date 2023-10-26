package com.AAACE.RUTidy.controller;


import com.AAACE.RUTidy.dto.*;
import com.AAACE.RUTidy.service.*;
import com.AAACE.RUTidy.model.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping(value = "/group", method={RequestMethod.GET , RequestMethod.POST} )
public class GroupController {

    @Autowired
    private UserService userService;

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


   

/** 
    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.login(loginDTO);
        return loginResponse;
    }

    */


    

}
