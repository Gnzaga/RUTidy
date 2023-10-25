package com.AAACE.RUTidy.controller;


import com.AAACE.RUTidy.dto.LoginDTO;
import com.AAACE.RUTidy.dto.LoginResponse;
import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.dto.UserDTO;
import com.AAACE.RUTidy.service.UserService;
import com.AAACE.RUTidy.dto.GroupDTO;
import com.AAACE.RUTidy.service.GroupService;


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

    @PostMapping(path = "/create")
    public Response createGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.createGroup(groupDTO);
    }  
/** 
    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.login(loginDTO);
        return loginResponse;
    }

    */


    

}
