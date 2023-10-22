package com.AAACE.RUTidy.model;

//imports:
//import com.AAACE.RUTidy.Users.User;
import com.AAACE.RUTidy.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
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
@Table(name = "Groups")
public class Group {

    /**
     * These are the fields for the status of the invitation.
     */


    /**
     * This is the field for the groupID.
     * It is generated upon the creation of agroup
     */
    @Id
    @Column(name = "groupID", length=255)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupID;
    
    /**
     * This is the field for the name of the group.
     * 
     * This is the name of the group, it can be the same as other group names
     */
    @Column(name = "`name`", length=255)
    private String name;

    /**
     * This is the field for the members of the group.
     * 
     * This is a list of the members of the group, containing the owner
     */
    @Column(name = "members", length=255)
    private ArrayList<User> members;

    /**
     * This is the field for the pending members of the group.
     * 
     * when members are added they will be removed from this list
     */
    @Column(name = "pendingMembers", length=255)
    private ArrayList<String> pendingMembers;


    /**
     * This is the field for the invitations sent to the group.
     * 
     */
    @OneToMany(mappedBy = "group", cascade=CascadeType.ALL)
    private ArrayList<Invitation> invitations;

    /**
     * This is the field for the admin of the group.
     */
    @Column(name = "owner", length=255)
    private User owner;
    
    /**
     * This is the field for the admin of the group.
     *
     * 0 for admin, 1 for user with manage, 2 for user without manage
     */
    @Column(name = "permissionLevels", length=255)
    private HashMap<User, Integer> permissionLevels; //0 for admin, 1 for user with manage, 2 for user without manage

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
        this.invitations = new ArrayList<Invitation>();
        this.permissionLevels = new HashMap<User, Integer>();

    }


    /**
     * This is the constructor for the Group class.
     * 
     * @param name String
     * @param owner User
     */
    public Group(String name, User owner){
        this.name = name;
        this.members = new ArrayList<User>();
        this.owner = owner;
        this.groupID = UUID.randomUUID().hashCode();
        this.pendingMembers = new ArrayList<String>();
        this.invitations = new ArrayList<Invitation>();
        this.permissionLevels = new HashMap<User, Integer>();
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

    public ArrayList<Invitation> getSentInvitations(){
        return this.invitations;
    }

    /**
     * This is the add 
     * @param email
     */
    public void addInvitation(String email) {
        invitations.add(new Invitation(email, this));
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
        this.invitations.add(new Invitation(memberEmail, this));
    }
    
    /**
     * This is the method to accept a member to the group
     * 
     * @param memberEmail String
     * @param member User
     *
     */
    public void acceptInvitation(User member){
  

        if(invitations.get(member.getEmail()) != SENT ||
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
