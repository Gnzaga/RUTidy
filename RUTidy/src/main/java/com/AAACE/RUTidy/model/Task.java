package com.AAACE.RUTidy.model;

import com.AAACE.RUTidy.constants.TaskConstants;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TemporalType;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import java.time.LocalDateTime;

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

 @Entity
public class Task {
    

    
    /**
     * These are the fields for the Task class.
     */
    
    @Column(name = "name", length=255)
    private String name;

    @Column(name = "description", length=255)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime assignedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dueDate;

    @Column(name = "priority", length=20)
    private String priority;

    @Column(name = "status", length=20)
    private String status;

    @Id
    @Column(name = "taskID", length=255)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskID;

    @ManyToMany
    @JoinTable(
        name = "user_task",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "userid")
    )
    private List<User> assignedUsers;

    @ManyToOne
    private Group group;

    

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

    public Task(String name, String description, LocalDateTime dueDate, String priority, String status, Group group){
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.assignedDate = LocalDateTime.now();
        this.priority = priority;
        this.status = status;
        this.taskID = UUID.randomUUID().hashCode();
        this.assignedUsers = new ArrayList<User>();
        this.group = group;
        //this.comments = new ArrayList<TaskComment>();
    }

    /**
     * This is the default constructor for the Task class.
     * 
     */
    public Task(){
        this.name = "";
        this.description = "";
        this.dueDate = LocalDateTime.now();
        this.assignedDate = LocalDateTime.now();
        this.priority = TaskConstants.LOW;
        this.status = TaskConstants.NOT_STARTED;
        this.taskID = UUID.randomUUID().hashCode();
        this.assignedUsers = new ArrayList<User>();
        //this.comments = new ArrayList<TaskComment>();
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
    public LocalDateTime getDueDate(){
        return this.dueDate;
    }

    /**
     * This is the setter for the dueDate field.
     * 
     * @param dueDate String
     */
    public void setDueDate(LocalDateTime dueDate){
        this.dueDate = dueDate;
    }

    /**
     * This is the overloaded setter for the dueDate field allwoing
     * a string to be inputted
     * @return
     */
    public void setDueDate(String dueDate){
        this.dueDate = LocalDateTime.parse(dueDate);
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
    public List<User> getAssignedUsers(){
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
     * This returns whether or not user is an assigned user
     * @param user
     */
    public boolean isAssignedUser(User user){
        return this.assignedUsers.contains(user);
    }

    /**
     * this allows you to assign a new user
     * @param user
     */

    public void assignUser(User user){
        this.assignedUsers.add(user);
    }

    /**
     * this allows you to remove a user
     * @param user
     */
    public void removeUser(User user){
        this.assignedUsers.remove(user);
    }

    /**
     * This is the getter for the assignedDate field.
     * 
     * @return LocalDateTime assignedDate
     */
    public LocalDateTime getAssignedDate(){
        return this.assignedDate;
    }

    /**
     * This is the setter for the assignedDate field.
     * 
     * @param assignedDate LocalDateTime
     */
    public void setAssignedDate(LocalDateTime assignedDate){
        this.assignedDate = assignedDate;
    }

    /**
     * This is the getter for the group field.
     * 
     * @return Group group
     */
    public Group getGroup(){
        return this.group;
    }


    /**
     * This is the getter for the comments field.
     * 
     * @return ArrayList<TaskComment> comments
     *
    public ArrayList<TaskComment> getComments(){
        return this.comments;
    }
*/


    /**
     * This is the addComment method. It adds a comment to the comments field.
     * 
     * @param comment String
     *
    public void addComment(String comment, String user){
        Date date = new Date();
        TaskComment taskComment = new TaskComment(comment, date, user);
        this.comments.add(taskComment);
    }

    /**
     * This is the removeComment method. It removes a comment from the comments field.
     * 
     * @param comment TaskComment object
     *
    public void removeComment(TaskComment comment){
        this.comments.remove(comment);
    }
        */

}

