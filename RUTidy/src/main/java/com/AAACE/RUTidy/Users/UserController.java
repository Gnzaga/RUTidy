package com.AAACE.RUTidy.Users;


import com.AAACE.RUTidy.Controllers.LoginResponse;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    //@Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }  

    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.login(loginDTO);
        return loginResponse;
    }


    

}
