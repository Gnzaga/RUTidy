package com.AAACE.RUTidy.service.task;

import com.AAACE.RUTidy.model.*;
import com.AAACE.RUTidy.repository.*;
import com.AAACE.RUTidy.constants.ResponseConstants;
import com.AAACE.RUTidy.dto.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TaskImplementation implements TaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TaskRepository taskRepository;


    /**
     * for most of these, the test for if 
     * the repository result is null is more of a formality,
     * but is good practice in case something gets deleted by accident
     * or if something gets sent in error
     */
    public Response createTask(TaskDTO taskDTO) {
        Group group = groupRepository
            .findByGroupID(taskDTO.getGroupID()).get();
        if(group == null){
            return new Response(
                ResponseConstants.
                GROUP_NOT_FOUND, 
                null);
        }

        Task task = new Task(
            taskDTO.getName(),
            taskDTO.getDescription(),
            taskDTO.getDueDate(),
            taskDTO.getPriority(),
            taskDTO.getStatus(),
            group
        );

        User user = userRepository
            .findByUserID(taskDTO.getUserID()).get();

        if(user == null){
            taskRepository.save(task);
            return new Response(
                ResponseConstants.USER_NOT_FOUND, 
                task);
        }
        task.assignUser(user);
        taskRepository.save(task);
        return new Response(
            ResponseConstants.SUCCESS, 
            task);
    }
    
    public Response updateTask(TaskDTO taskDTO, int taskID) {
        Task task = taskRepository
            .findByTaskID(taskID).get();
        if(task == null){
            return new Response(
                ResponseConstants.TASK_NOT_FOUND, 
                null);
        }
        //sets all, if same value it will effectively not change.
        //front end needs to just autofill the dto with
        //prior information
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());

        taskRepository.save(task);

        return new Response(
            ResponseConstants.SUCCESS, 
            task);
    }

    public Response updateTaskStatus(int taskID, String status, int reportingUserID){
        Task task = taskRepository
            .findByTaskID(taskID).get();
        if(task == null){
            return new Response(
                ResponseConstants.TASK_NOT_FOUND, 
                null);
        }

        User user = userRepository
            .findByUserID(reportingUserID).get();
        
        if(user == null){
            return new Response(
                ResponseConstants.USER_NOT_FOUND, 
                null);
        }

        task.setStatus(status);

        taskRepository.save(task);

        return new Response(
            ResponseConstants.SUCCESS, 
            task);
    }

    public Response updateTaskPriority(int taskID, String priority){
        Task task = taskRepository
            .findByTaskID(taskID).get();
        if(task == null){
            return new Response(
                ResponseConstants.TASK_NOT_FOUND, 
                null);
        }

        task.setPriority(priority);

        taskRepository.save(task);

        return new Response(
            ResponseConstants.SUCCESS, 
            task);
    }

    public Response updateTaskDueDate(int taskID, String dueDate){
        Task task = taskRepository
            .findByTaskID(taskID).get();
        if(task == null){
            return new Response(
                ResponseConstants.TASK_NOT_FOUND, 
                null);
        }
        
        task.setDueDate(dueDate);

        taskRepository.save(task);

        return new Response(
            ResponseConstants.SUCCESS, 
            task);
    }

    public Response updateTaskDescription(int taskID, String description){
        Task task = taskRepository
            .findByTaskID(taskID).get();
        if(task == null){
            return new Response(
                ResponseConstants.TASK_NOT_FOUND, 
                null);
        }
        
        task.setDescription(description);

        taskRepository.save(task);

        return new Response(
            ResponseConstants.SUCCESS, 
            task);
    }

    public Response updateTaskName(int taskID, String name){
        Task task = taskRepository
            .findByTaskID(taskID).get();
        if(task == null){
            return new Response(
                ResponseConstants.TASK_NOT_FOUND, 
                null);
        }
        
        task.setName(name);

        taskRepository.save(task);

        return new Response(
            ResponseConstants.SUCCESS, 
            task);
    }

    public Response getTask(int taskID) {
        Task task = taskRepository
            .findByTaskID(taskID).get();

        if(task == null){
            return new Response(
                ResponseConstants.TASK_NOT_FOUND, 
                null);
        }

        return new Response(
            ResponseConstants.SUCCESS, 
            task);
    }

    public Response deleteTask(int taskID){
        Task task = taskRepository
            .findByTaskID(taskID).get();
        if(task == null){
            return new Response(
                ResponseConstants.TASK_NOT_FOUND, 
                null);
        }

        taskRepository.delete(task);

        return new Response(
            ResponseConstants.SUCCESS, 
            null);
    }

    public Response getUserTasks(int userID) {
        User user = userRepository
            .findByUserID(userID).get();
        if(user == null){
            return new Response(
                ResponseConstants.USER_NOT_FOUND, 
                null);
        }

        List<Task> tasks = taskRepository
            .findByAssignedUsers(user);

        return new Response(
            ResponseConstants.SUCCESS, 
            tasks);
    }

    public Response getGroupTasks(int groupID) {
        Group group = groupRepository
            .findByGroupID(groupID).get();
        if(group == null){
            return new Response(
                ResponseConstants.GROUP_NOT_FOUND, 
                null);
        }

        List<Task> tasks = taskRepository
            .findByGroup(group);
        
        return new Response(
            ResponseConstants.SUCCESS, 
            tasks);
    }

    public Response getUsersTasksInGroup(int userID, int groupID, String userTimeZone) {
        User user = userRepository
            .findByUserID(userID).get();

        if(user == null){
            return new Response(
                ResponseConstants.USER_NOT_FOUND, 
                null);
        }

        Group group = groupRepository
            .findByGroupID(groupID).get();

        if(group == null){
            return new Response(
                ResponseConstants.GROUP_NOT_FOUND, 
                null);
        }

        List<Task> tasks = taskRepository
            .findByAssignedUsersAndGroup(user, group);
        List<Task> tasksInUserTimeZone = tasks.stream()
            .map(task -> convertTaskDateTimeToUserTimeZone(task, userTimeZone))
            .collect(Collectors.toList());
        
        return new Response(
            ResponseConstants.SUCCESS, 
            tasksInUserTimeZone);
    }

    private Task convertTaskDateTimeToUserTimeZone(Task task, String userTimeZone) {
    LocalDateTime taskDateTime = task.getDueDate();
    // Convert LocalDateTime to ZonedDateTime in UTC
    ZonedDateTime utcDateTime = ZonedDateTime.of(taskDateTime, ZoneId.of("UTC"));
    // Convert UTC ZonedDateTime to ZonedDateTime in user's local timezone
    ZonedDateTime userLocalDateTime = utcDateTime.withZoneSameInstant(ZoneId.of(userTimeZone));

    // Set the converted ZonedDateTime back to the task
    task.setDueDate(userLocalDateTime.toLocalDateTime());

    return task;
}

    public Response assignUser(int taskID, int userID){
        Task task = taskRepository
            .findByTaskID(taskID).get();
        if(task == null){
            return new Response(
                ResponseConstants.TASK_NOT_FOUND, 
                null);
        }

        User user = userRepository
            .findByUserID(userID).get();
        if(user == null){
            return new Response(
                ResponseConstants.USER_NOT_FOUND, 
                null);
        }

        if(task.isAssignedUser(user)){
            return new Response(
                ResponseConstants.USER_ALREADY_ASSIGNED_TASK, 
                null);
        }

        task.assignUser(user);

        taskRepository.save(task);

        return new Response(
            ResponseConstants.SUCCESS, 
            task);
    }

    public Response removeUser(int taskID, int userID){
        Task task = taskRepository
            .findByTaskID(taskID).get();
        if(task == null){
            return new Response(
                ResponseConstants.TASK_NOT_FOUND, 
                null);
        }

        User user = userRepository
            .findByUserID(userID).get();
        if(user == null){
            return new Response(
                ResponseConstants.USER_NOT_FOUND, 
                null);
        }

        if(!task.isAssignedUser(user)){
            return new Response(
                ResponseConstants.USER_NOT_ASSIGNED_TASK, 
                null);
        }

        task.removeUser(user);

        taskRepository.save(task);

        return new Response(
            ResponseConstants.SUCCESS, 
            task);

    }

   
}
