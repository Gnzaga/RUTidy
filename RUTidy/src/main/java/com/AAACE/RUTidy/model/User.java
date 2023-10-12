package com.AAACE.RUTidy.model;

import java.util.ArrayList;
import java.util.UUID;

//import com.AAACE.RUTidy.Groups.Group;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


/**
     * 
     * This is the User class. It contains the following fields:
     * <ul>
     * <li>firstName</li>
     * <li>lastName</li>
     * <li>email</li>
     * <li>password</li>
     * <li>username</li>
     * </ul>
     * 
     * @param name
     * @param email
     * @param password
     * @param username
     * 
     * @author Alessandro Gonzaga [amg573]
*/  

@Entity
@Table(name = "Users")
public class User {

    @Column(name = "name", length=255)
    private String name;

    @Column(name = "email", length=255)
    private String email;

    @Column(name = "password", length=255)
    private String password;

    @Column(name = "username", length=25)
    private String username;
    
    @Id
    @Column(name = "userID", length=255)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;
    
    //private ArrayList<Group> groups;
    //private ArrayList<String> pendingInvitations;

    
    /**
     * This is the constructor for the User class.
     * 
     * @param name
     * @param email
     * @param password
     * @param username
     */



    public User(String name, String email, String password, String username){
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.userID = UUID.randomUUID().hashCode();
        //this.groups = new ArrayList<Group>();
        //this.pendingInvitations = new ArrayList<String>();
    }
    //default constructor

    public User(){
        this.name = "";
        this.email = "";
        this.password = "";
        this.username = "";
        this.userID = UUID.randomUUID().hashCode();
        //this.groups = new ArrayList<Group>();
        //this.pendingInvitations = new ArrayList<String>();
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
     * This is the getter for the email field.
     * 
     * @return String email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * This is the getter for the password field.
     * 
     * @return String password
     */
    public String getPassword(){
        return this.password;
    }
    /**
     * This is the getter for the username field.
     *
     * @return String username
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * This is the getter for the userID field.
     *
     * @return int userID
     */

    public int getUserID(){
        return this.userID;
    }

    /**
     * This is the getter for the groups field.
     *
     * @return ArrayList<Group> groups
     

    public ArrayList<Group> getGroups(){
        return this.groups;
    }
    */
    /**
     * This is the getter for the pendingInvitations field.
     *
     * @return ArrayList<String> pendingInvitations
     */

     /**
    public ArrayList<String> getPendingInvitations(){
        return this.pendingInvitations;
    }
    */

    /**
     *  This is the setter for the name field.
     * @param name
     */

    public void setName(String name){
        this.name = name;
    }

    /**
     *  This is the setter for the email field.
     * @param email
     */

    public void setEmail(String email){
        this.email = email; 
    }

    /**
     *  This is the setter for the password field.
     * @param password
     */

    public void setPassword(String password){
        this.password = password;
    }

    /**
     *  This is the setter for the username field.
     * @param username
     */

    public void setUsername(String username){
        this.username = username;
    }

    /**
     *  This is the method add a group to the list of joined groups.
     * @param group
     

    public void addGroup(Group group){
        this.groups.add(group);
    }

    /**
     *  This is the method to remove a group from the list of joined groups.
     * @param group
     

    public void removeGroup(Group group){
        this.groups.remove(group);
    }
    */

    /**
     * This is the method to create your own group
     * @param name
     

    public void createGroup(String name){
        Group group = new Group(name, User.this );
        this.groups.add(group);
    }
    */


    /**
     * This is the toString method for the User class.
     * 
     * @return String
     */

    @Override
    public String toString(){
        return "User{" + "name=" + name + ", email=" + email + ", password=" + password + ", username=" + username + "}";
    }


}
