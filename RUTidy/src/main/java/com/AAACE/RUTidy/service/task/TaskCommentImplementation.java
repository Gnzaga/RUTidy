package com.AAACE.RUTidy.service.task;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.AAACE.RUTidy.dto.*;
import com.AAACE.RUTidy.repository.*;
import com.AAACE.RUTidy.model.*;
import com.AAACE.RUTidy.constants.ResponseConstants;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class TaskCommentImplementation implements TaskCommentService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCommentRepository taskCommentRepository;


    public Response createTaskComment(TaskCommentDTO taskCommentDTO){
        Optional<User> optionalUser = userRepository.findByUserID(taskCommentDTO.getUserID());
        //check if user exists
        if(optionalUser.isEmpty()){
            return new Response(ResponseConstants.USER_NOT_FOUND, null);
        }
        User user = optionalUser.get();

        Task task = taskRepository.findByTaskID(taskCommentDTO.getTaskID()).get();
        //check if task exists
        if(task == null){
            return new Response(ResponseConstants.TASK_NOT_FOUND, null);
        }

        TaskComment taskComment = new TaskComment(
            taskCommentDTO.getComment(),
            user,
            task
        );

        taskCommentRepository.save(taskComment);

        return new Response(ResponseConstants.SUCCESS, taskComment);
    }

    public Response getTaskComment(int taskCommentID){
        Optional<TaskComment> optionalTaskComment = taskCommentRepository.findByCommentID(taskCommentID);
        //check if task comment exists
        if(optionalTaskComment.isEmpty()){
            return new Response(ResponseConstants.TASK_COMMENT_NOT_FOUND, null);
        }

        TaskComment taskComment = optionalTaskComment.get();

        return new Response(ResponseConstants.SUCCESS, taskComment);
    }
    

    public Response getTaskComments(int taskID){
        Optional<Task> optionalTask = taskRepository.findByTaskID(taskID);
        //check if task exists
        if(optionalTask.isEmpty()){
            return new Response(ResponseConstants.TASK_NOT_FOUND, null);
        }  

        Task task = optionalTask.get();

        List<TaskComment> taskComments = taskCommentRepository.findByTask(task);

        return new Response(ResponseConstants.SUCCESS, taskComments);
    }


    public Response getTaskCommentsSorted(int taskID){
        Optional<Task> optionalTask = taskRepository.findByTaskID(taskID);
        //check if task exists
        if(optionalTask.isEmpty()){
            return new Response(ResponseConstants.TASK_NOT_FOUND, null);
        }

        Task task = optionalTask.get();

        List<TaskComment> taskComments = taskCommentRepository.findByTask(task);

        taskComments.sort((TaskComment t1, TaskComment t2) -> t1.getDate().compareTo(t2.getDate()));

        return new Response(ResponseConstants.SUCCESS, taskComments);
    }

    public Response getTaskCommentsByUserID(int userID){
        Optional<User> optionalUser = userRepository.findByUserID(userID);
        //check if user exists
        if(optionalUser.isEmpty()){
            return new Response(ResponseConstants.USER_NOT_FOUND, null);
        }
        User user = optionalUser.get();

        List<TaskComment> taskComments = taskCommentRepository.findByAuthor(user);

        return new Response(ResponseConstants.SUCCESS, taskComments);
    }

    public Response updateTaskComment(TaskCommentDTO taskCommentDTO){
        Optional<User> optionalUser = userRepository.findByUserID(taskCommentDTO.getUserID());
        //check if user exists
        if(optionalUser.isEmpty()){
            return new Response(ResponseConstants.USER_NOT_FOUND, null);
        }
        User user = optionalUser.get();

        Task task = taskRepository.findByTaskID(taskCommentDTO.getTaskID()).get();
        //check if task exists
        if(task == null){
            return new Response(ResponseConstants.TASK_NOT_FOUND, null);
        }
        TaskComment taskComment = taskCommentRepository.findByCommentID(taskCommentDTO.getTaskID()).get();
        //check if task comment exists
        if(taskComment == null){
            return new Response(ResponseConstants.TASK_COMMENT_NOT_FOUND, null);
        }


        taskComment.editComment(taskCommentDTO.getComment());

        taskCommentRepository.save(taskComment);

        return new Response(ResponseConstants.SUCCESS, taskComment);
    }

    public Response deleteTaskComment(int taskCommentID){
        Optional<TaskComment> optionalComment = taskCommentRepository.findByCommentID(taskCommentID);
        //check if task comment exists
        if(optionalComment.isEmpty()){
            return new Response(ResponseConstants.TASK_COMMENT_NOT_FOUND, null);
        }

        TaskComment taskComment = optionalComment.get();

        taskCommentRepository.delete(taskComment);

        return new Response(ResponseConstants.SUCCESS, null);
    }

    public Response getAuthoredComments(int userID){
        Optional<User> optionalUser = userRepository.findByUserID(userID);
        //check if user exists
        if(optionalUser.isEmpty()){
            return new Response(ResponseConstants.USER_NOT_FOUND, null);
        }
        User user = optionalUser.get();

        List<TaskComment> taskComments = taskCommentRepository.findByAuthor(user);

        return new Response(ResponseConstants.SUCCESS, taskComments);
    }    

    public Response getAuthoredCommentsInTask(int userID, int taskID){
        Optional<User> optionalUser = userRepository.findByUserID(userID);
        //check if user exists
        if(optionalUser.isEmpty()){
            return new Response(ResponseConstants.USER_NOT_FOUND, null);
        }
        User user = optionalUser.get();

        Task task = taskRepository.findByTaskID(taskID).get();
        //check if task exists
        if(task == null){
            return new Response(ResponseConstants.TASK_NOT_FOUND, null);
        }

        List<TaskComment> taskComments = taskCommentRepository.findByAuthorAndTask(user, task);

        return new Response(ResponseConstants.SUCCESS, taskComments);
    }
    
}
