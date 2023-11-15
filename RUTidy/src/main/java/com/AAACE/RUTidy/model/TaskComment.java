package com.AAACE.RUTidy.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Table;
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
@Entity
@Table(name="TaskComments")
public class TaskComment {

    /**
     * These are the fields for the TaskComment class.
     */
    
    @Column(name = "comment", length=255)
    private String comment;

    @Column(name = "isEdited")
    private Boolean isEdited;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    @ManyToOne
    @JoinColumn(name = "userID")
    private User author;

    @ManyToOne
    @JoinColumn(name="taskID")
    private Task task;

    @Id
    @Column(name = "commentID", length=255)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    /**
     * This is the constructor for the TaskComment class.
     * 
     * @param comment
     * @param date
     * @param user
     */
    public TaskComment(String comment, User user, Task task){
        this.comment = comment;
        this.date = Date.from(java.time.Instant.now());
        this.author = user;
        this.task = task;
        this.commentID = UUID.randomUUID().hashCode();
        this.isEdited = false;
    }

    /**
     * This is the default constructor for the TaskComment class.
     */
    public TaskComment(){}


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
     * @return date of the comment posted
     */
    public Date getDate(){
        return this.date;
    }

    /**
     * This is the getter for the user field.
     * 
     * @return user that is the author of the comment
     */
    public User getAuthor(){
        return this.author;
    }

    /**
     * This is the getter for the task field
     * 
     * @return the task the task this comment is on
     */
    public Task getTask(){
        return this.task;
    }

    /**
     * This is the function to edit a comment
     * @param comment the new comment to be set
     */
    public void editComment(String comment){
        this.comment = comment;
        this.isEdited = true;
    }

    /**
     * This compare to will override normal compare to to allow comments to be sorted by date
     */
    public int compareTo(TaskComment other){
        return this.date.compareTo(other.date);
    }

    /**
     * This is the toString method for the TaskComment class.
     */
    @Override
    public String toString(){
        return this.comment + " " + this.date + " " + this.author;
    }


}
