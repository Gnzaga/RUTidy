package com.AAACE.RUTidy.service.group;


import java.util.HashMap;

import com.AAACE.RUTidy.dto.*;


public interface UsersInGroupService {
    Response addUserToGroup(UserInGroupDTO userInGroupDTO);
    HashMap<Integer, Integer> getAllUsersRoleInGroup(int groupID);

}
