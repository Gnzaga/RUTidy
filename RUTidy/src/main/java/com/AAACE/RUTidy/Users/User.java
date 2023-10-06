


package com.AAACE.RUTidy.Users;

public class User {

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
     */  
  
    private String name;
    private String email;
    private String password;
    private String username;
    private int permissionLevel; //0 for admin, 1 for user with manage, 2 for user without manage

    //how can I handle a user being in multiple groups?
    //-> I can have a list of groups that the user is in
    //and then I can have a list of users in the group
    //but what about different permission levels for each group?
    //-> I can have a list of permission levels for each group
    


    public User(String name, String email, String password, String username){
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.permissionLevel = 2;
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
     * This is the getter for the permissionLevel field.
     *
     * @return int permissionLevel
     */
    public int getPermissionLevel(){
        return this.permissionLevel;
    }

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
     *  This is the setter for the permissionLevel field.
     * @param permissionLevel
     */

    public void setPermissionLevel(int permissionLevel){
        this.permissionLevel = permissionLevel;
    }

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
