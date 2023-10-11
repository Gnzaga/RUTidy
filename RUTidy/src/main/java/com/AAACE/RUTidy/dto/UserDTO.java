package com.AAACE.RUTidy.dto;

public class UserDTO {
    private int userID;
    private String name;
    private String email;
    private String password;
    private String username;

    public UserDTO() {
    }

    public UserDTO(int userID, String name, String email, String password, String username) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
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
     * This is the getter for the email field.
     * 
     * @return String email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * This is the setter for the email field.
     * 
     * @param email
     */
    public void setEmail(String email){
        this.email = email;
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
     * This is the setter for the password field.
     * 
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
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
     * This is the setter for the username field.
     * 
     * @param username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * This is the getter for the userID field.
     * 
     * @return int userID
     */
    public int getUserID(){
        return this.userID;
    }

    // no setter for user id


    

    
}
