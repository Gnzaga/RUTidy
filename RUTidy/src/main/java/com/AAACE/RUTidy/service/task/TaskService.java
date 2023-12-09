package com.AAACE.RUTidy.service.task;

import com.AAACE.RUTidy.model.*;
import com.AAACE.RUTidy.repository.*;
import com.AAACE.RUTidy.dto.*;

import java.util.List;

public interface TaskService {

    /**
     * Creates a task
     * @param task
     * @return
     */
    Response createTask(TaskDTO taskDTO);


    /**
     * updates a task
     * @param taskID
     * @return
     */
    Response updateTask(TaskDTO taskDTO, int taskID);

    /**
     * Updates Task Status
     * @param taskID
     * @param status
     * @param reportingUserID
     * @return
     */
    Response updateTaskStatus(int taskID, String status, int reportingUserID);

    /**
     * Updates Task Priority
     * @param taskID
     * @param priority
     * @return
     */
    Response updateTaskPriority(int taskID, String priority);

    /**
     * Updates task due date
     * @param taskID
     * @param dueDate
     * @return
     */
    Response updateTaskDueDate(int taskID, String dueDate);
    
    /**
     * Update task description
     * @param taskID
     * @param description
     * @return
     */

    Response updateTaskDescription(int taskID, String description);

    /**
     * Update task name
     * @param taskID
     * @param name
     * @return
     */
    Response updateTaskName(int taskID, String name);
    /**
     * Returns particular task
     * @param taskID
     * @return
     */
    Response getTask(int taskID);

    /**
     * Deletes a task
     * @param taskID
     * @return
     */
    Response deleteTask(int taskID);

    /**
     * Gets all tasks from this user
     * @param userID
     * @return result String + task list
     */
    Response getUserTasks(int userID);

    /**
     * Gets all tasks from this group
     * @param groupID
     * @return Result string + task list
     */
    Response getGroupTasks(int groupID);

    /**
     * Gets all tasks from this user in this group
     * @param userID
     * @param groupID
     * @return
     */
    Response getUsersTasksInGroup(int userID, int groupID, String userTimeZone);

    /**
     * Adds a new user to this group
     * @param userID
     * @param groupID
     * @return
     */
    Response assignUser(int taskID, int userID);

    /**
     * Removes a user from thisg roup
     * @param userID
     * @param groupID
     * @return
     */
     Response removeUser(int taskID, int userID);

    /**
     * Gets all tasks from this user in this group
     * @param userID
     * @param groupID
     * @return
     */
    //Response getTasksByStatus(int userID, String status);

    /**
     * Gets all tasks from this user in this group
     * @param userID
     * @param groupID
     * @return
     */
    //List<Task> getTasksByStatusInGroup(int userID, String status, int groupID);

    /**
     * Gets all tasks from this user in this group
     * @param userID
     * @param groupID
     * @return
     */
    //List<Task> getTasksByStatusInGroup(int groupID, String status);



}
