package com.AAACE.RUTidy.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AAACE.RUTidy.model.*;
import com.AAACE.RUTidy.dto.*;
import com.AAACE.RUTidy.service.*;


@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private GroupService groupService;

    @Autowired 
    private TaskService taskService;

    /**
     * This will create a new task.
     * 
     * The taskDTO requires the following fields:
     * - taskName
     * - taskDescription
     * - taskDueDate
     * - taskPriority - see com.AAACE.RUTidy.constants.TaskConsants.java
     * - taskStatus - see com.AAACE.RUTidy.constants.TaskConsants.java
     * - taskGroupID - this is the group the task is being assigned to
     * - taskUserID - this is the user being initially assigned the task
     * 
     * @param taskDTO
     * @return
     */
    @PostMapping("/create")
    public Response createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    /**
     * This will update the task with the specified taskID
     * 
     * Be advised, that the front end should send the entire taskDTO object,
     * with non-updated fields being the same as the original task
     * @param taskDTO 
     * @param taskID
     * @return
     */
    @PutMapping("/update")
    public Response updateTask(@RequestBody TaskDTO taskDTO, @RequestParam int taskID) {
        return taskService.updateTask(taskDTO, taskID);
    }

    /**
     * This will update the task status with the specified taskID
     * 
     * please see com.AAACE.RUTidy.constants.TaskConstants.java for constants
     * @param taskID (int)
     * @param taskStatus (String)
     * @param userID (int)
     * @return Response [message, object]
     */
    @PutMapping("/update-status")
    public Response updateTaskStatus(@RequestParam int taskID, @RequestParam String taskStatus, @RequestParam int userID) {
        return taskService.updateTaskStatus(taskID, taskStatus, userID);
    }

    /**
     * This will update the task with a new prioritied for the specified taskid
     * @param taskID (int)
     * @param priority (String)
     * @return
     */
    @PutMapping("/update-priority")
    public Response updateTaskPriority(@RequestParam int taskID, @RequestParam String priority) {
        return taskService.updateTaskPriority(taskID, priority);
    }

    /**
     * This will assign a new user to the task
     * @param taskID
     * @param userID
     * @return
     */
    @PutMapping("/assign-user")
    public Response assignUser(@RequestParam int taskID, @RequestParam int userID) {
        return taskService.assignUser(taskID, userID);
    }

    /**
     * This will remove a user from the task
     * @param taskID
     * @param userID
     * @return
     */
    @PutMapping("/remove-user")
    public Response removeUser(@RequestParam int taskID, @RequestParam int userID) {
        return taskService.removeUser(taskID, userID);
    }

    /**
     * This will update the task due date with the specified taskID
     * @param taskID
     * @param dueDate
     * @return
     */
    @PutMapping("/update-due-date")
    public Response updateTaskDueDate(@RequestParam int taskID, @RequestParam String dueDate) {
        return taskService.updateTaskDueDate(taskID, dueDate);
    }

    /**
     * This will update the task description
     * @param taskID
     * @param description
     * @return
     */
    @PutMapping("/update-description")
    public Response updateTaskDescription(@RequestParam int taskID, @RequestParam String description) {
        return taskService.updateTaskDescription(taskID, description);
    }

    /**
     * This will update teh task name
     * @param taskID
     * @param taskName
     * @return
     */
    @PutMapping("/update-name")
    public Response updateTaskName(@RequestParam int taskID, @RequestParam String taskName) {
        return taskService.updateTaskName(taskID, taskName);
    }







    /**
     * This will return the task with the specified taskID
     * @param taskID
     * @return
     */
    @GetMapping("/get")
    public Response getTask(@RequestParam int taskID) {
        return taskService.getTask(taskID);
    }

    /**
     * This will delete the task with the specified taskID
     * @param taskID
     * @return
     */
    @DeleteMapping("/delete")
    public Response deleteTask(@RequestParam int taskID) {
        return taskService.deleteTask(taskID);
    }

    /**
     * this will return all the tasks associated with this user
     * @param userID
     * @return
     */
    @GetMapping("/get-user-tasks")
    public Response getUserTasks(@RequestParam int userID) {
        return taskService.getUserTasks(userID);
    }

    /**
     * This will return all the tasks
     * associated with this group
     * @param groupID
     * @return
     */
    @GetMapping("/get-group-tasks")
    public Response getGroupTasks(@RequestParam int groupID) {
        return taskService.getGroupTasks(groupID);
    }

    /**
     * Gets all tasks for a user in a group, ie it will return 
     * all tasks that are assigned to the specified user in the specified group
     */
    @GetMapping("/get-group-tasks-by-user")
    public Response getGroupTasksByUser(@RequestParam int userID, @RequestParam int groupID, @RequestParam String userTimeZone) {
        return taskService.getUsersTasksInGroup(userID, groupID, userTimeZone);
    }


    /**
     * 
     */





}
