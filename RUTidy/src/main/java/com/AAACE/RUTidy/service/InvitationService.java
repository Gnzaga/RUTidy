package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.model.Invitation;

import com.mysql.jdbc.log.Log;

import java.util.List;

public interface InvitationService {
    Invitation getInvitation(String email, int groupID);

    List<Invitation> getUsersInvitations(String email);

    List<Invitation> getGroupsInvitations(int groupID);

    Invitation addInvitation(Invitation invitation);

    Invitation updateInvitation(Invitation invitation);

    void deleteInvitation(String email, int groupID);

    
}
