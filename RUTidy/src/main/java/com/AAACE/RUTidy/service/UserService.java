package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.dto.LoginDTO;
import com.AAACE.RUTidy.dto.LoginResponse;
import com.AAACE.RUTidy.dto.UserDTO;
import com.mysql.jdbc.log.Log;

public interface UserService {
    LoginResponse addUser(UserDTO user);
    LoginResponse login(LoginDTO login);
    LoginResponse updateUser(UserDTO user, int userID);
}
