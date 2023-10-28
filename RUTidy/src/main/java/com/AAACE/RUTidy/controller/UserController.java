package com.AAACE.RUTidy.controller;


import com.AAACE.RUTidy.dto.LoginDTO;
import com.AAACE.RUTidy.dto.LoginResponse;
import com.AAACE.RUTidy.dto.UserDTO;
import com.AAACE.RUTidy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping(value = "/user", method={RequestMethod.GET , RequestMethod.POST} )
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public LoginResponse saveUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }  

    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.login(loginDTO);
        return loginResponse;
    }

    @PostMapping(path = "/update")
    public LoginResponse updateUser(@RequestBody UserDTO userDTO, @RequestParam int userID) {
        LoginResponse loginResponse = userService.updateUser(userDTO, userID);
        return loginResponse;
    }
    

}
