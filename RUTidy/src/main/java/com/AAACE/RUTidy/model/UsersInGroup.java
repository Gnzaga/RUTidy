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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "UsersInGroup")
public class UsersInGroup {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int UIGroupID;

    @ManyToOne
    @JoinColumn(name = "groupID")
    private Group group;

    @Column(name = "roles", length=255)
    private int roles; //0 = user, 1 = user with manage, 2 = admin


    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    

    /**
     * This is the constructor for the Group class.
     * 
     * @param group Group to add user to
     * @param user User to add to group
     */

    public UsersInGroup(Group group , User user){
        this.group = group;
        this.user = user;
        this.UIGroupID = UUID.randomUUID().hashCode();
    }

    /**
     * This is the default constructor for the Group class.
     */ 

    public UsersInGroup(){
        this.group = null;
        this.user = null;
        this.UIGroupID = UUID.randomUUID().hashCode();
    }





    public int getUIGroupID(){
        return this.UIGroupID;
    }


    public User getUser(){
        return this.user;
    }


    public Group getGroup(){
        return this.group;
    }

    /**
     * This is the toString method for the Group class.
     * 
     * @return String
     */

    public String toString(){
        return "Group: " + this.group + "\nUser in group: " + this.user;
    }



}