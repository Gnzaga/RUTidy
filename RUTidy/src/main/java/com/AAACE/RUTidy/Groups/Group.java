package com.AAACE.RUTidy.Groups;

//imports:
import com.AAACE.RUTidy.Users.User;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

    /**

     * This is the Group class. It contains the following fields:
     * <ul>
     * <li>name</li>
     * <li>members</li>
     * <li>admin</li>
     * </ul>
     * 
     * @param name
     * @param members
     * @param admin
     * @author Alessandro Gonzaga [amg573]
     */
public class Group {


    final String NOT_SENT = "not sent";
    final String SENT = "sent";
    final String ACCEPTED = "accepted";
    final String REJECTED = "rejected";


    private int groupID;
    
    private String name;

    private ArrayList<User> members;

    private ArrayList<String> pendingMembers;

    private HashMap<String, String> sentInvitations;

    private User admin;

    private HashMap<User, Integer> permissionLevels; //0 for admin, 1 for user with manage, 2 for user without manage

    public Group(String name, ArrayList<User> members, User admin){
        this.name = name;
        this.members = members;
        this.admin = admin;
        this.groupID = UUID.randomUUID().hashCode();
        this.pendingMembers = new ArrayList<String>();
        this.sentInvitations = new HashMap<String, String>();
        this.permissionLevels = new HashMap<User, Integer>();

    }

    public Group(String name, User admin){
        this.name = name;
        this.admin = admin;
        this.members = new ArrayList<User>();
        this.members.add(admin);
        this.pendingMembers = new ArrayList<String>();
        this.sentInvitations = new HashMap<String, String>();
        this.permissionLevels = new HashMap<User, Integer>();
        this.permissionLevels.put(admin, 0);
        this.groupID = UUID.randomUUID().hashCode();

    }

    //getters and setters

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
     * This is the getter for the admin field.
     * 
     * @return String admin
     */

    public User getAdmin(){
        return this.admin;
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
     * This is the setter for the name field.
     * 
     * @param name
     */

    public void setName(String name){
        this.name = name;
    }

    /**
     * This is the setter for the members field.
     * 
     * @param members
     */

    public void setMembers(ArrayList<User> members){
        this.members = members;
    }
    
    /**
     * This is the method to add members to the group.
     * 
     * @param member
     */
    public void addMember(User member){
        this.members.add(member);
        this.permissionLevels.put(member, 2);
    }

    /**
     * This is the method to grant a member admin privileges.
     * 
     * @param member
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
     * @param member
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
     * @param memberEmail
     *
     */
    public void inviteMember(String memberEmail){
        this.pendingMembers.add(memberEmail);
        this.sentInvitations.put(memberEmail, NOT_SENT);
    }
    
    /**
     * This is the method to accept a member to the group
     * 
     * @param memberEmail
     * @param member
     *
     */
    public void acceptInvitation(String memberEmail, User member){
        this.pendingMembers.remove(memberEmail);
        this.sentInvitations.put(memberEmail, ACCEPTED);
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
        this.members.remove(member);
    }
    
    /**
     * This is the setter for the admin field.
     * 
     * @param admin
     */

    public void setAdmin(User admin){
        this.admin = admin;
    }

    /**
     * This is the toString method for the Group class.
     * 
     * @return String
     */

    public String toString(){
        return "Group: " + this.name + "\nMembers: " + this.members + "\nAdmin: " + this.admin;
    }




}
