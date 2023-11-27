package com.AAACE.RUTidy.controller;

import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.model.Invitation;
import com.AAACE.RUTidy.service.invitation.InvitationService;
import com.AAACE.RUTidy.constants.ResponseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/invitations")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;


    @GetMapping("/{email}/{groupID}")
    public Response getInvitation(@PathVariable String email, @PathVariable int groupID) {
        return invitationService.getInvitation(email, groupID);
    }

    @GetMapping("/{invitationID}")
    public Response getInvitation(@PathVariable int invitationID) {
        return invitationService.getInvitation(invitationID);
    }

    @GetMapping("/user/{email}")
    public Response getUsersInvitations(@PathVariable String email) {
        return invitationService.getUsersInvitations(email);
    }

    @GetMapping("/group/{groupID}")
    public Response getGroupsInvitations(@PathVariable int groupID) {
        return invitationService.getGroupsInvitations(groupID);
    }

    @PostMapping("/create/{groupID}/{email}")
    public Response addInvitation(@PathVariable int groupID, @PathVariable String email) {
        return invitationService.addInvitation(groupID, email);
    }

    @PutMapping("/update")
    public Response updateInvitation(@RequestBody Invitation invitation) {
        return invitationService.updateInvitation(invitation);
    }

    

    @PutMapping("/accept/{invitationID}")
    public Response acceptInvitation(@PathVariable int invitationID) {
        return invitationService.acceptInvitation(invitationID);
    }

    @PutMapping("/reject/{invitationID}")
    public Response rejectInvitation(@PathVariable int invitationID) {
        return invitationService.rejectInvitation(invitationID);
    }

}   