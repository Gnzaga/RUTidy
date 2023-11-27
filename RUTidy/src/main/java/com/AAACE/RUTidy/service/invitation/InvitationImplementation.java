package com.AAACE.RUTidy.service.invitation;

import org.springframework.stereotype.Service;

import com.AAACE.RUTidy.repository.GroupRepository;
import com.AAACE.RUTidy.repository.InvitationRepository;
import com.AAACE.RUTidy.repository.UserRepository;

import ch.qos.logback.core.pattern.color.RedCompositeConverter;

import com.AAACE.RUTidy.model.Invitation;
import com.AAACE.RUTidy.model.Group;
import com.AAACE.RUTidy.model.User;
import com.AAACE.RUTidy.constants.ResponseConstants;
import com.AAACE.RUTidy.dto.Response;

import com.AAACE.RUTidy.service.group.GroupService;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class InvitationImplementation implements InvitationService{
    
    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired GroupService groupService;

    public Response getInvitation(String email, int groupID) {
        Optional<Group> optionalGroup = groupRepository.findByGroupID(groupID);
        if(optionalGroup.isEmpty()) {
            return null;
        }
        Group group = optionalGroup.get();

        Optional<Invitation> optionalInvitation = invitationRepository.findByEmailAndGroup(email, group);
        Invitation invitation = optionalInvitation.get();


        return new Response(ResponseConstants.SUCCESS, invitation);

    }

    public Response getInvitation(int invitationID) {
        try{
            Optional<Invitation> optionalInvitation = invitationRepository.findById(invitationID);
            if(optionalInvitation.isEmpty()) {
                return new Response(ResponseConstants.INVITATION_NOT_FOUND, null);
            }
            Invitation invitation = optionalInvitation.get();
            return new Response(ResponseConstants.SUCCESS, invitation);
        } catch (Exception e) {
            return new Response(ResponseConstants.ERROR, null);
        }
    }

    public Response getUsersInvitations(String email) {
        try{
            List<Invitation> invitations = invitationRepository.findByEmail(email);

            //trim rejected invites
            for( int i = 0; i < invitations.size(); i++) {
                if(invitations.get(i).getStatus() == Invitation.REJECTED) {
                    invitations.remove(i);
                    System.out.println("removed: " + i);
                }
            }
            return new Response(ResponseConstants.SUCCESS, invitations);
        } catch (Exception e) {
            return new Response(ResponseConstants.ERROR, null);
        }
    }

    public Response getGroupsInvitations(int groupID) {
        try{
            List<Invitation> invitations = invitationRepository.findByGroupGroupID(groupID);
            return new Response(ResponseConstants.SUCCESS, invitations);
        } catch (Exception e) {
            return new Response(ResponseConstants.ERROR, null);
        }
    }

    public Response addInvitation(Invitation invitation) {
        try{
            Optional<Group> optionalGroup = groupRepository.findByGroupID       (invitation.getGroup().getGroupID());
            if(optionalGroup.isEmpty()) {
                return new Response(ResponseConstants.GROUP_NOT_FOUND, null);
            }
            Group group = optionalGroup.get();
            Optional<Invitation> optionalInvitation = invitationRepository.findByEmailAndGroup(invitation.getEmail(), group);
            if(optionalInvitation.isPresent()) {
                return new Response(ResponseConstants.INVITATION_ALREADY_EXISTS, null);
            }

            Optional<User> optionalUser = userRepository.findByEmail(invitation.getEmail());
            if(optionalUser.isPresent()){
                invitation.setUser(optionalUser.get());
            }
            invitation.setGroup(group);
            invitationRepository.save(invitation);
            return new Response(ResponseConstants.SUCCESS, invitation);
        } catch (Exception e) {
            return new Response(ResponseConstants.ERROR, null);
        }
    }

    public Response addInvitation(int groupID, String email) {
        try{
            Optional<Group> optionalGroup = groupRepository.findByGroupID(groupID);
            if(optionalGroup.isEmpty()) {
                return new Response(ResponseConstants.GROUP_NOT_FOUND, null);
            }
            Group group = optionalGroup.get();
            Optional<Invitation> optionalInvitation = invitationRepository.findByEmailAndGroup(email, group);
            if(optionalInvitation.isPresent()) {
                return new Response(ResponseConstants.INVITATION_ALREADY_EXISTS, null);
            }

            Optional<User> optionalUser = userRepository.findByEmail(email);
            if(optionalUser.isPresent()){
                Invitation invitation = new Invitation(email, group);
                invitation.setUser(optionalUser.get());
                invitationRepository.save(invitation);
                return new Response(ResponseConstants.SUCCESS, invitation);
            }
            Invitation invitation = new Invitation(email, group);
            invitationRepository.save(invitation);
            return new Response(ResponseConstants.SUCCESS, invitation);
        } catch (Exception e) {
            return new Response(ResponseConstants.ERROR, null);
        }
    }

    
    public Response updateInvitation(Invitation invitation) {
        try{
            Optional<Invitation> optionalInvitation = invitationRepository.findById(invitation.getInvitationID());
            if(optionalInvitation.isEmpty()) {
                return new Response(ResponseConstants.INVITATION_NOT_FOUND, null);
            }
            Invitation oldInvitation = optionalInvitation.get();
            oldInvitation.setStatus(invitation.getStatus());
            invitationRepository.save(oldInvitation);
            return new Response(ResponseConstants.SUCCESS, oldInvitation);
        } catch (Exception e) {
            return new Response(ResponseConstants.ERROR, null);
        }
    }
    
/* 
    public Response deleteInvitation (int invitationID) {
        try{
            Optional<Invitation> optionalInvitation = invitationRepository.findById(invitationID);
            if(optionalInvitation.isEmpty()) {
                return new Response(ResponseConstants.INVITATION_NOT_FOUND, null);
            }
            Invitation invitation = optionalInvitation.get();
            invitationRepository.deleteInvitation(invitation);
            return new Response(ResponseConstants.SUCCESS, null);
        } catch (Exception e) {
            return new Response(ResponseConstants.ERROR, null);
        }
    }

    public Response deleteInvitation(Invitation invitation) {
        try{
            Optional<Invitation> optionalInvitation = invitationRepository.findById(invitation.getInvitationID());
            if(optionalInvitation.isEmpty()) {
                return new Response(ResponseConstants.INVITATION_NOT_FOUND, null);
            }

            invitationRepository.deleteByInvitationID(invitation.getInvitationID());
            return new Response(ResponseConstants.SUCCESS, null);
        } catch (Exception e) {
            return new Response(ResponseConstants.ERROR, null);
        }
    }
    */

    public Response acceptInvitation(int invitationID){
        try{
            Optional<Invitation> optionalInvitation = invitationRepository.findById(invitationID);
            if(optionalInvitation.isEmpty()) {
                return new Response(ResponseConstants.INVITATION_NOT_FOUND, null);
            }
            Invitation invitation = optionalInvitation.get();
            invitation.setStatus(Invitation.ACCEPTED);
            invitationRepository.save(invitation);

            Group group = invitation.getGroup();
            User user = invitation.getUser();
            return groupService.addUserToGroup(group, user);

           
        } catch (Exception e) {
            return new Response(ResponseConstants.ERROR, null);
        }
    }

    public Response rejectInvitation(int invitationID){
        try{
            
            Optional<Invitation> optionalInvitation = invitationRepository.findById(invitationID);
            if(optionalInvitation.isEmpty()) {
                return new Response(ResponseConstants.INVITATION_NOT_FOUND, null);
            }
            Invitation invitation = optionalInvitation.get();
            invitation.setStatus(Invitation.REJECTED);
            invitationRepository.save(invitation);
            return new Response(ResponseConstants.SUCCESS, invitation);
        } catch (Exception e) {
            return new Response(ResponseConstants.ERROR, null);
        }
    }
    






}
