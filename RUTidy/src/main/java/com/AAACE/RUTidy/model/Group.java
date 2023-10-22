package com.AAACE.RUTidy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


    /**

     * This is the Group class. It contains the following fields:
     * <ul>
     * <li>name</li>
     * <li>members</li>
     * <li>admin</li>
     * </ul>
     * 
     * @param name String
     * @param members ArrayList<User>
     * @param admin User
     * @author Alessandro Gonzaga [amg573]
     */

@Entity
@Table(name = "Users")

public class Group implements Serializable{

    final String NOT_SENT = "not sent";
    final String SENT = "sent";
    final String ACCEPTED = "accepted";
    final String REJECTED = "rejected";

    @Column(name = "groupID", length = 10)
    private int groupID;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "members", length = 15)
    private ArrayList<User> members;

    @Column(name = "pendingMembers", length = 15)
    private ArrayList<String> pendingMembers;

    @Column(name = "sentInvitations", length = 15)
    private HashMap<String, String> sentInvitations;

    @Column(name = "owner", length = 15)
    private User owner;

    @Column(name = "permissionLevels", length = 15)
    private HashMap<User, Integer> permissionLevels;

    /**
     * This is the constructor for the Group class.
     * 
     * @param name String
     * @param members ArrayList<User> 
     * @param admin User
     */

    public Group(String name, ArrayList<User> members, User owner){
        this.name = name;
        this.members = members;
        this.owner = owner;
        this.groupID = UUID.randomUUID().hashCode();
        this.pendingMembers = new ArrayList<String>();
        this.sentInvitations = new HashMap<String, String>();
        this.permissionLevels = new HashMap<User, Integer>();
    }

    /**
     * This is the constructor for Group class.
     * @param name
     * @param members
     * @param owner
     * @param groupID
     * @param pendingMembers
     * @param sentInvitations
     * @param permissionLevels
     */

    public Group(String name, ArrayList<User> members, User owner, int groupID, ArrayList<String> pendingMembers, 
    HashMap<String, String> sentInvitations, HashMap<User, Integer> permissionLevels){
        this.name = name;
        this.members = members;
        this.owner = owner;
        this.groupID = groupID;
        this.pendingMembers = pendingMembers;
        this.sentInvitations = sentInvitations;
        this.permissionLevels = permissionLevels;
    }


    /**
     * This is the constructor for the Group class.
     * 
     * @param name String
     * @param owner User
     */
    public Group(String name, User owner){
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



    /**
     * This is the getter for the name field.
     * 
     * @return String name
     */

    public String getName(){
        return this.name;
    }

    /**
     * This is the getter for the members field.
     * 
     * @return ArrayList<User> members
     */

    public ArrayList<User> getMembers(){
        return this.members;
    }

    /**
     * This is the getter for the owner field.
     * 
     * @return User owner
     */

    public User getOwner(){
        return this.owner;
    }

    /**
     * This is the getter for the groupID field.
     * 
     * @return int groupID
     */

    public int getGroupID(){
        return this.groupID;
    }

    /**
     * This is the getter for the pendingMembers field.
     * 
     * @return ArrayList<String> pendingMembers
     */

    public ArrayList<String> getPendingMembers(){
        return this.pendingMembers;
    }

    /**
     * This is the getter for the sentInvitations field.
     * 
     * @return HashMap<String, Boolean> sentInvitations
     */

    public HashMap<String, String> getSentInvitations(){
        return this.sentInvitations;
    }

    /**
     * This is the getter for the permissionLevels field.
     * @return HashMap<User, Integer> getPermissionLevels
     */
    public HashMap<User, Integer> getPermissionLevels(){
        return this.permissionLevels;
    }

    /**
     * This is the setter for the name field.
     * 
     * @param name String
     */

    public void setName(String name){
        this.name = name;
    }

    /**
     * This is the setter for the members field.
     * 
     * @param members ArrayList<User>
     */

    public void setMembers(ArrayList<User> members){
        this.members = members;
    }
    
    /**
     * This is the method to add members to the group.
     * 
     * @param member User
     */
    public void addMember(User member){
        if(this.members.contains(member)){
            System.out.println("This user is already a member of this group.");
            return;
        }
        this.members.add(member);
        this.permissionLevels.put(member, 2);
    }

    /**
     * This is the method to grant a member admin privileges.
     * 
     * @param member User
     */
    public void grantAdmin(User member){
        if(this.members.contains(member)){
            this.permissionLevels.put(member, 0);
        }
        else{
            System.out.println("This user is not a member of this group.");
        }
    }

    /**
     * This is the method to grant a member manage privileges.
     * 
     * @param member User
     */
     
    public void grantManage(User member){
        if(this.members.contains(member)){
            this.permissionLevels.put(member, 1);
        }
        else{
            System.out.println("This user is not a member of this group.");
        }
    }

    
 
    /**
     * This is the method to invite a member to the group
     * 
     * @param memberEmail String
     *
     */
    public void inviteMember(String memberEmail){
        this.pendingMembers.add(memberEmail);
        this.sentInvitations.put(memberEmail, NOT_SENT);
    }
    
    /**
     * This is the method to accept a member to the group
     * 
     * @param memberEmail String
     * @param member User
     *
     */
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

    /**
     * This is the method to reject a member to the group
     * 
     * @param memberEmail
     *
     */
    public void rejectInvitation(String memberEmail){
        this.pendingMembers.remove(memberEmail);
        this.sentInvitations.put(memberEmail, REJECTED);
    }

    /**
     * This is the method to notify the admin that the invitation was sent
     * 
     * @param memberEmail
     *
     */
    public void inviteSent(String memberEmail){
        this.sentInvitations.put(memberEmail, SENT);
    }

    /**
     * This is the method to remove a member from the group
     * 
     * @param member
     *
     */
    public void removeMember(User member){
        if(members.contains(member)){
            this.members.remove(member);
            this.permissionLevels.remove(member);
        }
        else{
            System.out.println("{member} is not a member of this group.");
        }
    }
    
    /**
     * This is the setter for the owner field
     * 
     * @param owner
     */
    public void setOwner(User owner){
        if(this.members.contains(owner)){
            this.owner = owner;
        }
        else{
            System.out.println("{owner} is not a member of this group.");
        }
    }



    /**
     * This is the toString method for the Group class.
     * 
     * @return String
     */

    public String toString(){
        return "Group: " + this.name + "\nMembers: " + this.members + "\nOwner: " + this.owner;
    }




}
