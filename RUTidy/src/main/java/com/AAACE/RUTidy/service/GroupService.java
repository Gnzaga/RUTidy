package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.dto.UserInGroupDTO;
import com.AAACE.RUTidy.model.UsersInGroup;
import com.AAACE.RUTidy.dto.GroupDTO;
import com.AAACE.RUTidy.model.Group;

import java.util.List;
import com.mysql.jdbc.log.Log;

public interface GroupService {
    /**
     * Creates a group from this ownerid
     * @param group
     * @param userID
     * @return
     */
    Response createGroup(GroupDTO group);
    Response updateUserPermission(UserInGroupDTO userInGroupDTO, int role);
    List<UsersInGroup> getGroupsIn(int userID);
    List<UsersInGroup> getUsersIn(int groupID);
    List<Group> findGroupByName(String name);
    String joinGroup(int groupID, int userID);
    void leaveGroup(int UIGroupID);
   
}