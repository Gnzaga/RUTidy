package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.dto.GroupDTO;

import com.mysql.jdbc.log.Log;

public interface GroupService {
    /**
     * Creates a group from this ownerid
     * @param group
     * @param userID
     * @return
     */
    Response createGroup(GroupDTO group);

   
}