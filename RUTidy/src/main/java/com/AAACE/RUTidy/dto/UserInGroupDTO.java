package com.AAACE.RUTidy.dto;

public class UserInGroupDTO {
    
    private int userID;
    private int groupID;

    /**
     * This is the constructor for the UserInGroupDTO class.
     * @param userID
     * @param groupID
     */
    public UserInGroupDTO(int userID, int groupID){
        this.userID = userID;
        this.groupID = groupID;
    }

    /**
     * This is the default constructor for the UserInGroupDTO class.
     */
    public UserInGroupDTO() {
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
     * This is the getter for the groupID field
     * 
     * @return int userID
     */

    public int getGroupID(){
        return this.groupID;
    }
    






}
