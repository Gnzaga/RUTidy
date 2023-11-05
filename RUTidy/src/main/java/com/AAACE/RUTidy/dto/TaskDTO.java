package com.AAACE.RUTidy.dto;

import java.time.LocalDateTime;

public class TaskDTO {
    private String name;

    private String description;

    private LocalDateTime dueDate;

    private String priority;

    private String status;

    private int userID;

    private int groupID;


    /**
     * TaskDTO will pull from task requests and store information to be 
     * inserted later for easier access.
     * @param name
     * @param description
     * @param dueDate
     * @param priority
     * @param status
     * @param userID
     * @param groupID
     */
    public TaskDTO(String name, String description, String dueDate, String priority, String status, int userID, int groupID) {
        this.name = name;
        this.description = description;
        //gets the date and time from the string and converts it to a LocalDateTime object
        this.dueDate = LocalDateTime.parse(dueDate);
        this.priority = priority;
        this.status = status;
        this.userID = userID;
        this.groupID = groupID;
    }

    public TaskDTO() {
    }
    
    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return LocalDateTime return the dueDate
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return String return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return String return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return int return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return int return the groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String toString() {
        return "TaskDTO [name=" + name + ", description=" + description + ", dueDate=" + dueDate + ", priority="
                + priority + ", status=" + status + ", userID=" + userID + ", groupID=" + groupID + "]";
    }
}
