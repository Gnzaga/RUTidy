package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.dto.LoginDTO;
import com.AAACE.RUTidy.dto.LoginResponse;
import com.AAACE.RUTidy.dto.UserDTO;

public interface UserService {
    String addUser(UserDTO user);
    LoginResponse login(LoginDTO login);
}
