package com.AAACE.RUTidy.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class GroupDTO{
    
    private String name;
    private int ownerID;
    /**
     * This is the groupID field.
     * -1 indicates no groupID given
     */
    private int groupID;
    
    /**
     * This is the constructor for the GroupDTO class.
     * @param name
     * @param ownerID
     */
    public GroupDTO(String name, int ownerID){
        this.name = name;
        this.ownerID = ownerID;
        this.groupID = -1;
    }

    /**
     * This is the constructor for the GroupDTO class.
     * @param name
     * @param ownerID
     * @param groupID
     */
    public GroupDTO(String name, int ownerID, int groupID){
        this.name = name;
        this.ownerID = ownerID;
        this.groupID = groupID;
    }

    /**
     * This is the default constructor for the GroupDTO class.
     */
    public GroupDTO() {
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
     * This is the setter for the name field.
     * 
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * This is the getter for the ownerID field.
     * 
     * @return int ownerID
     */
    public int getOwnerID(){
        return this.ownerID;
    }

    /**
     * This is the setter for the ownerID field.
     * 
     * @param ownerID
     */
    public void setOwnerID(int ownerID){
        this.ownerID = ownerID;
    }

    /**
     * This is the getter for the groupID field.
     * 
     * @return int groupID
     */
    public int getGroupID(){
        return this.groupID;
    }
}