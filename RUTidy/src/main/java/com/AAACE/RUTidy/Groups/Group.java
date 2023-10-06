package com.AAACE.RUTidy.Groups;

//imports:
import com.AAACE.RUTidy.Users.User;
import com.AAACE.RUTidy.Users.Admin;


import java.lang.reflect.Array;
import java.util.ArrayList;


public class Group {
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
     * 
     */

    private String name;

    private ArrayList<User> members;

    private Admin admin;

    public Group(String name, ArrayList<User> members, Admin admin){
        this.name = name;
        this.members = members;
        this.admin = admin;
    }

    public Group(String name, Admin admin){
        this.name = name;
        this.admin = admin;

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

    public Admin getAdmin(){
        return this.admin;
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
    }

    /**
     * This is the setter for the admin field.
     * 
     * @param admin
     */

    public void setAdmin(Admin admin){
        this.admin = admin;
    }



}
