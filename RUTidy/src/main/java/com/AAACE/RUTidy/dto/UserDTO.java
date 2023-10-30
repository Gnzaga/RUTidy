package com.AAACE.RUTidy.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String username;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String password, String username) {
        this.name = name;
        this.email = email;
        if(password.length() == 0) this.password = null;
        else this.password = this.passwordEncoder.encode(password);
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
     * @deprecated
     */
    /*public void setPassword(String password){
        this.password = password;
    }*/

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

    public String toString(){
        return this.username + " " + this.password + " " + this.email + " " + this.name;
    }
    

    
}
