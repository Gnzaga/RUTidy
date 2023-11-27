package com.AAACE.RUTidy.dto;



public class TaskCommentDTO {
    
    private int userID;

    private String comment;

    private int taskID;

    private int taskCommentID;

    /**
     * TaskCommentDTO Default constructor
     */
    public TaskCommentDTO() {
    }

    /**
     * TaskCommentDTO constructor for creation
     * @param userID
     * @param comment
     * @param taskID
     */
    public TaskCommentDTO(String comment, int userID, int taskID) {
        this.userID = userID;
        this.comment = comment;
        this.taskID = taskID;
    }

    /**
     * TaskCommentDTO constructor for update
     * @param comment
     * @param taskCommentID
     */    
    public TaskCommentDTO(String comment, int userID, int taskID, int taskCommentID){
        this.taskCommentID = taskCommentID;
        this.userID = userID;
        this.comment = comment;
        this.taskID = taskID;
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
     * @return String return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return int return the taskID
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String toString(){
        return "TaskCommentDTO: {userID: " + userID + ", comment: " + comment + ", taskID: " + taskID + "}";
    }

}
