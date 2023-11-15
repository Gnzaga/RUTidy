package com.AAACE.RUTidy.service.invitation;

import org.springframework.stereotype.Service;

import com.AAACE.RUTidy.repository.GroupRepository;
import com.AAACE.RUTidy.repository.InvitationRepository;
import com.AAACE.RUTidy.model.Invitation;
import com.AAACE.RUTidy.model.Group;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class InvitationImplementation implements InvitationService{
    
    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private GroupRepository groupRepository;

    public Invitation getInvitation(String email, int groupID) {
        Optional<Group> optionalGroup = groupRepository.findByGroupID(groupID);
        if(optionalGroup.isEmpty()) {
            return null;
        }
        Group group = optionalGroup.get();

        Optional<Invitation> optionalInvitation = invitationRepository.findByEmailAndGroup(email, group);

        if(optionalInvitation.isEmpty()) {
            return null;
        }
        return optionalInvitation.get();
    }

    public List<Invitation> getUsersInvitations(String email) {
        return invitationRepository.findByEmail(email);
    }

    public List<Invitation> getGroupsInvitations(int groupID) {
        return invitationRepository.findByGroupGroupID(groupID);
    }

    public Invitation addInvitation(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    public Invitation updateInvitation(Invitation invitation) {
        return invitationRepository.save(invitation);
    }
/**
    public void deleteInvitation(Invitation invitationID) {
        invitationRepository.deleteInvitation(invitationID);
    }
    */






}
