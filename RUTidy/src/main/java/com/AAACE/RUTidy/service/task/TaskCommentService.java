package com.AAACE.RUTidy.service.task;

import com.AAACE.RUTidy.dto.*;

/**
 * TaskCommentService
 */
public interface TaskCommentService {
    

    /**
     * Creates a task comment
     */
    Response createTaskComment(TaskCommentDTO taskCommentDTO);

    /**
     * Updates a task comment
     */
    Response updateTaskComment(TaskCommentDTO taskCommentDTO);

    /**
     * Deletes a task comment
     */

    Response deleteTaskComment(int taskCommentID);

    /**
     * Gets all task comments of a task
     */

    Response getTaskComments(int taskID);

    /**
     * Gets all task comments of a task in order of time posted
     */
    Response getTaskCommentsSorted(int taskID);

    /**
     * Gets a task comment
     */

    Response getTaskComment(int taskCommentID);

    /**
     * Getss all of a user's task comments
     */
    Response getAuthoredComments(int userID);

    /**
     * Gets all of a user's comments in a task
     */

    Response getAuthoredCommentsInTask(int userID, int taskID);





}
