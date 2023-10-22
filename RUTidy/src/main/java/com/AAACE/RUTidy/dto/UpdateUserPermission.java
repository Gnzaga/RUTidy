package com.AAACE.RUTidy.dto;

import com.AAACE.RUTidy.model.Group;
import com.AAACE.RUTidy.model.User;

public class UpdateUserPermission {
    String message;
    Group group;
    User user;

    public UpdateUserPermission(String message, Group group, User user){
        this.message = message;
        this.group = group;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Group getGroup(){
        return group;
    }

    public void setGroup(Group group){
        this.group = group;
    }
}
