package com.AAACE.RUTidy.service.user;

import com.AAACE.RUTidy.dto.LoginDTO;
import com.AAACE.RUTidy.dto.LoginResponse;
import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.dto.UserDTO;
import com.AAACE.RUTidy.model.User;

public interface UserService {
    LoginResponse addUser(UserDTO user);
    LoginResponse login(LoginDTO login);
    LoginResponse updateUser(UserDTO user, int userID);
    User getUser(int userID);
    Response getAllUsers();
}
