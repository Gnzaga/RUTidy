package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.model.Invitation;

import com.AAACE.RUTidy.dto.LoginDTO;
import com.AAACE.RUTidy.dto.LoginResponse;
import com.AAACE.RUTidy.dto.UserDTO;
import com.mysql.jdbc.log.Log;

import java.util.List;

public interface InvitationService {
    Invitation getInvitation(String email, int groupID);

    List<Invitation> getUsersInvitations(String email);

    List<Invitation> getGroupsInvitations(int groupID);

    Invitation addInvitation(Invitation invitation);

    Invitation updateInvitation(Invitation invitation);

    //void deleteInvitation(Invitation invitation);

    
}
