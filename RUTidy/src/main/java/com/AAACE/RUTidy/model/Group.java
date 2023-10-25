package com.AAACE.RUTidy.model;

//imports:
//import com.AAACE.RUTidy.Users.User;
import com.AAACE.RUTidy.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    private int ID;
    
    /**
     * This is the field for the name of the group.
     * 
     * This is the name of the group, it can be the same as other group names
     */
    @Column(name = "`name`", length=255)
    private String name;



    /**
     * This is the field for the owner of the group.
     */
    @ManyToOne
    @JoinColumn(name = "userID")
    private User owner;
    

    /**
     * This is the constructor for the Group class.
     * 
     * @param name String
     * @param members ArrayList<User> 
     * @param admin User
     */

    public Group(String name, ArrayList<User> members, User owner){
        this.name = name;
        this.owner = owner;
        this.ID = UUID.randomUUID().hashCode();
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
        this.ID = UUID.randomUUID().hashCode();
    }

    /**
     * This is the default constructor for the Group class.
     * 
     */
    public Group(){
        this.name = "";
        this.owner = null;
        this.ID = UUID.randomUUID().hashCode();
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

//   public ArrayList<User> getMembers(){
//        return this.members;
//    }

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
        return this.ID;
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
     * This is the setter for the owner field
     * 
     * @param owner
     */
    public void setOwner(User owner){
        /* 
        if(this.members.contains(owner)){
            this.owner = owner;
        }
        else{
            System.out.println("{owner} is not a member of this group.");
        }
        */
        this.owner = owner;
    }



    /**
     * This is the toString method for the Group class.
     * 
     * @return String
     */

    public String toString(){
        return "Group: " + this.name + "\nOwner: " + this.owner;
    }



}