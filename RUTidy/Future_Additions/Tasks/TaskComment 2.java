package com.AAACE.RUTidy.Tasks;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * This is the TaskComment class. It contains the following fields:
 * <ul>
 * <li>comment</li>
 * <li>date</li>
 * <li>User</li>
 * </ul>
 * 
 * @param comment
 * @param date
 * @param user
 * 
 */
public class TaskComment {

    /**
     * These are the fields for the TaskComment class.
     */
    private String comment;
    private Date date;
    private String user;

    /**
     * This is the constructor for the TaskComment class.
     * 
     * @param comment
     * @param date
     * @param user
     */
    public TaskComment(String comment, Date date, String user){
        this.comment = comment;
        this.date = date;
        this.user = user;
    }

    /**
     * This is the getter for the comment field.
     * 
     * @return comment
     */
    public String getComment(){
        return this.comment;
    }

    /**
     * This is the getter for the date field.
     * 
     * @return date
     */
    public Date getDate(){
        return this.date;
    }

    /**
     * This is the getter for the user field.
     * 
     * @return user
     */
    public String getUser(){
        return this.user;
    }

    /**
     * This is the toString method for the TaskComment class.
     */
    @Override
    public String toString(){
        return this.comment + " " + this.date + " " + this.user;
    }


}
