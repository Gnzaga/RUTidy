package com.AAACE.RUTidy.Controllers;

import com.AAACE.RUTidy.Users.User;
public class LoginResponse {
    String message;
    User user;

     public LoginResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public User getUser() {
        return user;
    }
    public void setStatus(User status) {
        this.user = user;
    }
   
}
