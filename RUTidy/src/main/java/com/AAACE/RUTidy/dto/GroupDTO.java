package com.AAACE.RUTidy.dto;
import com.AAACE.RUTidy.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupDTO {
    
    final String NOT_SENT = "not sent";
    final String SENT = "sent";
    final String ACCEPTED = "accepted";
    final String REJECTED = "rejected";

    private int groupID;
    private String name;
    private ArrayList<User> members;
    private ArrayList<String> pendingMembers;
    private HashMap<String, String> sentInvitations;
    private User owner;
    private HashMap<User, Integer> permissionLevels;


    public GroupDTO(){

    }


    public GroupDTO(String name, ArrayList<User> members, User owner){
        this.name = name;
        this.members = members;
        this.owner = owner;
        this.groupID = UUID.randomUUID().hashCode();
        this.pendingMembers = new ArrayList<String>();
        this.sentInvitations = new HashMap<String, String>();
        this.permissionLevels = new HashMap<User, Integer>();
    }


    public GroupDTO(String name, User owner){
        this.name = name;
        this.owner = owner;
        this.members = new ArrayList<User>();
        this.members.add(owner);
        this.pendingMembers = new ArrayList<String>();
        this.sentInvitations = new HashMap<String, String>();
        this.permissionLevels = new HashMap<User, Integer>();
        this.permissionLevels.put(owner, 0);
        this.groupID = UUID.randomUUID().hashCode();
    }


    public String getName(){
        return this.name;
    }


    public ArrayList<User> getMembers(){
        return this.members;
    }


    public User getOwner(){
        return this.owner;
    }


    public int getGroupID(){
        return this.groupID;
    }


    public ArrayList<String> getPendingMembers(){
        return this.pendingMembers;
    }


    public HashMap<String, String> getSentInvitations(){
        return this.sentInvitations;
    }

    public HashMap<User, Integer> getPermissionLevels(){
        return this.permissionLevels;
    }


    public void setName(String name){
        this.name = name;
    }


    public void setMembers(ArrayList<User> members){
        this.members = members;
    }


    public void addMember(User member){
        if(this.members.contains(member)){
            System.out.println("This user is already a member of this group.");
            return;
        }
        this.members.add(member);
        this.permissionLevels.put(member, 2);
    }


    public void grantAdmin(User member){
        if(this.members.contains(member)){
            this.permissionLevels.put(member, 0);
        }
        else{
            System.out.println("This user is not a member of this group.");
        }
    }


    public void grantManage(User member){
        if(this.members.contains(member)){
            this.permissionLevels.put(member, 1);
        }
        else{
            System.out.println("This user is not a member of this group.");
        }
    }


    public void inviteMember(String memberEmail){
        this.pendingMembers.add(memberEmail);
        this.sentInvitations.put(memberEmail, NOT_SENT);
    }


    public void acceptInvitation(User member){
        if(sentInvitations.get(member.getEmail()) != SENT ||
            !this.pendingMembers.contains(member.getEmail())){
            System.out.println("This user has not been invited to this group.");
            return;
        }
        else if(sentInvitations.get(member.getEmail()) == ACCEPTED){
            System.out.println("This user has already accepted the invitation.");
            return;
        }
        else if(sentInvitations.get(member.getEmail()) == REJECTED){
            System.out.println("This user has already rejected the invitation.");
            return;
        }
        this.pendingMembers.remove(member.getEmail());
        this.sentInvitations.put(member.getEmail(), ACCEPTED);
        this.members.add(member);
    }


    public void rejectInvitation(String memberEmail){
        this.pendingMembers.remove(memberEmail);
        this.sentInvitations.put(memberEmail, REJECTED);
    }


    public void inviteSent(String memberEmail){
        this.sentInvitations.put(memberEmail, SENT);
    }


    public void removeMember(User member){
        if(members.contains(member)){
            this.members.remove(member);
            this.permissionLevels.remove(member);
        }
        else{
            System.out.println("{member} is not a member of this group.");
        }
    }


    public void setOwner(User owner){
        if(this.members.contains(owner)){
            this.owner = owner;
        }
        else{
            System.out.println("{owner} is not a member of this group.");
        }
    }


    public String toString(){
        return "Group: " + this.name + "\nMembers: " + this.members + "\nOwner: " + this.owner;
    }

    
}
