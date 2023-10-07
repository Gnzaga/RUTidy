package com.AAACE.RUTidy.Tasks;

import java.util.UUID;

import com.AAACE.RUTidy.Users.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * This is the Task class. It contains the following fields:
 * <ul>
 * <li>name</li>
 * <li>description</li>
 * <li>dueDate</li>
 * <li>priority</li>
 * <li>status</li>
 * </ul>
 * 
 * @param name
 * @param description
 * @param dueDate
 * @param priority
 * @param status
 */
public class Task {
    
    final String NOT_STARTED = "not started";
    final String IN_PROGRESS = "in progress";
    final String COMPLETED = "completed";
    /**
     * These are the fields for the Task class.
     */
    private String name;
    private String description;
    private String dueDate;
    private String priority;
    private String status;
    private int taskID;

    private ArrayList<User> assignedUsers;

    private ArrayList<TaskComment> comments;

    /**
     * This is the constructor for the Task class.
     * 
     * @param name
     * @param description
     * @param dueDate
     * @param priority
     * @param status
     * @param groupID
     * @param taskID
     */
    public Task(String name, String description, String dueDate, String priority, String status){
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.taskID = UUID.randomUUID().hashCode();
        this.assignedUsers = new ArrayList<User>();
        this.comments = new ArrayList<TaskComment>();

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
     * This is the setter for the name field.
     * 
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * This is the getter for the description field.
     * 
     * @return String description
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * This is the setter for the description field.
     * 
     * @param description String
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * This is the getter for the dueDate field.
     * 
     * @return String dueDate
     */
    public String getDueDate(){
        return this.dueDate;
    }

    /**
     * This is the setter for the dueDate field.
     * 
     * @param dueDate String
     */
    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
    }

    /**
     * This is the getter for the priority field.
     * 
     * @return String priority
     */
    public String getPriority(){
        return this.priority;
    }

    /**
     * This is the setter for the priority field.
     * 
     * @param priority String
     */
    public void setPriority(String priority){
        this.priority = priority;
    }

    /**
     * This is the getter for the status field.
     * 
     * @return String status
     */
    public String getStatus(){
        return this.status;
    }

    /**
     * This is the setter for the status field.
     * 
     * @param status String
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * This is the getter for the taskID field.
     * 
     * @return int taskID
     */
    public int getTaskID(){
        return this.taskID;
    }

    /**
     * This is the setter for the taskID field.
     * 
     * @param taskID int
     */
    public void setTaskID(int taskID){
        this.taskID = taskID;
    }

    /**
     * This is the getter for the assignedUsers field.
     * 
     * @return ArrayList<User> assignedUsers
     */
    public ArrayList<User> getAssignedUsers(){
        return this.assignedUsers;
    }

    /**
     * This is the setter for the assignedUsers field.
     * 
     * @param assignedUsers ArrayList<User>
     */
    public void setAssignedUsers(ArrayList<User> assignedUsers){
        this.assignedUsers = assignedUsers;
    }

    /**
     * This is the getter for the comments field.
     * 
     * @return ArrayList<TaskComment> comments
     */
    public ArrayList<TaskComment> getComments(){
        return this.comments;
    }



    /**
     * This is the addComment method. It adds a comment to the comments field.
     * 
     * @param comment String
     */
    public void addComment(String comment, String user){
        Date date = new Date();
        TaskComment taskComment = new TaskComment(comment, date, user);
        this.comments.add(taskComment);
    }

    /**
     * This is the removeComment method. It removes a comment from the comments field.
     * 
     * @param comment TaskComment object
     */
    public void removeComment(TaskComment comment){
        this.comments.remove(comment);
    }
        

}

