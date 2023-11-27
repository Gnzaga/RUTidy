package com.AAACE.RUTidy.service.invitation;

import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.model.Invitation;


import java.util.List;

public interface InvitationService {
    Response getInvitation(String email, int groupID);

    Response getInvitation(int invitationID);

    Response getUsersInvitations(String email);

    Response getGroupsInvitations(int groupID);

    Response addInvitation(Invitation invitation);

    Response addInvitation(int groupID, String email);

    Response updateInvitation(Invitation invitation);

/* 
    Response deleteInvitation (int invitationID);

    Response deleteInvitation(Invitation invitation);
*/
    Response acceptInvitation(int invitationID);

    Response rejectInvitation(int invitationID);
    
}
