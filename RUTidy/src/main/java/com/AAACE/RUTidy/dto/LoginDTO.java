package com.AAACE.RUTidy.dto;
/**
 * This is the DTO for the login request.
 * 
 */
public class LoginDTO {
    private String email;
    private String password;

  

    public LoginDTO() {
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
        //System.out.println("LoginDTO PW: " + this.password);
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

}
