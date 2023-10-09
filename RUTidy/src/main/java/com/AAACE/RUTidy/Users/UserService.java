package com.AAACE.RUTidy.Users;

import com.AAACE.RUTidy.Controllers.LoginResponse;

public interface UserService {
    String addUser(UserDTO user);
    LoginResponse login(LoginDTO login);
}
