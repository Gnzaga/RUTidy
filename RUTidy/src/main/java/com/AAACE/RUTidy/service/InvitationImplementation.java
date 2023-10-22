package com.AAACE.RUTidy.service;

import org.springframework.stereotype.Service;

import com.AAACE.RUTidy.repository.InvitationRepository;
import com.AAACE.RUTidy.model.Invitation;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Service
public class InvitationImplementation implements InvitationService{
    
    @Autowired
    private InvitationRepository invitationRepository;

    public Invitation getInvitation(String email, int groupID) {
        Optional<Invitation> optionalInvitation = invitationRepository.findByEmailAndGroup(email, groupID);
        if(optionalInvitation.isEmpty()) {
            return null;
        }
        return optionalInvitation.get();
    }

    public List<Invitation> getUsersInvitations(String email) {
        return invitationRepository.findByEmail(email);
    }

    public List<Invitation> getGroupsInvitations(int groupID) {
        return invitationRepository.(groupID);
    }

    public Invitation addInvitation(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    public Invitation updateInvitation(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    public void deleteInvitation(String email, int groupID) {
        invitationRepository.deleteInvitation(email, groupID);
    }






}
