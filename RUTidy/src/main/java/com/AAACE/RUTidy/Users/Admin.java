package com.AAACE.RUTidy.Users;

public class Admin extends User{
    
   /**
     * @author Alessandro Gonzaga [amg573]
     * This is the User class. It contains the following fields:
     * <ul>
     * <li>firstName</li>
     * <li>lastName</li>
     * <li>email</li>
     * <li>password</li>
     * <li>username</li>
     * <li>permissionLevel</li>
     * </ul>
     * 
     * @param name
     * @param email
     * @param password
     * @param username
     * 
     * 
     */

    private int permissionLevel; //0 for admin, 1 for user with manage, 2 for user without manage
    
    public Admin(String name, String email, String password, String username){
        super(name, email, password, username);
        this.permissionLevel = 0;
    }
    Admin
    //getters and setters

    /**
     * This is the getter for the name field.
     * 
     * @return String name
     */
    public String getName(){
        return super.getName();
    }

    /**
     * This is the getter for the email field.
     * 
     * @return String email
     */

    public String getEmail(){
        return super.getEmail();
    }

    /**
     * This is the getter for the password field.
     * 
     * @return String password
     */

    public String getPassword(){
        return super.getPassword();
    }

    /**
     * This is the getter for the username field.
     * 
     * @return String username
     */

    public String getUsername(){
        return super.getUsername();
    }

    /**
     * This is the getter for the permissionLevel field.
     * 
     * @return int permissionLevel
     */

    public int getPermissionLevel(){
        return this.permissionLevel;
    }




}
