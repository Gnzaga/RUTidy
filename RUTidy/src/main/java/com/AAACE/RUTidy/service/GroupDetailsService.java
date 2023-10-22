package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.dto.UpdateUserPermission;
import com.AAACE.RUTidy.dto.GroupDTO;
import com.AAACE.RUTidy.dto.UserDTO;
import com.mysql.jdbc.log.Log;

public interface GroupDetailsService {
    UpdateUserPermission grantMember(GroupDTO group, UserDTO user);
    UpdateUserPermission grantManage(GroupDTO group, UserDTO user);
    UpdateUserPermission grantAdmin(GroupDTO group, UserDTO user);
}
