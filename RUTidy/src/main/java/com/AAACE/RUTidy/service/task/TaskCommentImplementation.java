package com.AAACE.RUTidy.service.task;

import org.springframework.stereotype.Service;

import java.util.List;

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
        User user = userRepository.findByUserID(taskCommentDTO.getUserID()).get();
        //check if user exists
        if(user == null){
            return new Response(ResponseConstants.USER_NOT_FOUND, null);
        }

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
        TaskComment taskComment = taskCommentRepository.findByCommentID(taskCommentID).get();
        //check if task comment exists
        if(taskComment == null){
            return new Response(ResponseConstants.TASK_COMMENT_NOT_FOUND, null);
        }

        return new Response(ResponseConstants.SUCCESS, taskComment);
    }
    

    public Response getTaskComments(int taskID){
        Task task = taskRepository.findByTaskID(taskID).get();
        //check if task exists
        if(task == null){
            return new Response(ResponseConstants.TASK_NOT_FOUND, null);
        }

        List<TaskComment> taskComments = taskCommentRepository.findByTask(task);

        return new Response(ResponseConstants.SUCCESS, taskComments);
    }


    public Response getTaskCommentsSorted(int taskID){
        Task task = taskRepository.findByTaskID(taskID).get();
        //check if task exists
        if(task == null){
            return new Response(ResponseConstants.TASK_NOT_FOUND, null);
        }

        List<TaskComment> taskComments = taskCommentRepository.findByTask(task);

        taskComments.sort((TaskComment t1, TaskComment t2) -> t1.getDate().compareTo(t2.getDate()));

        return new Response(ResponseConstants.SUCCESS, taskComments);
    }

    public Response getTaskCommentsByUserID(int userID){
        User user = userRepository.findByUserID(userID).get();
        //check if user exists
        if(user == null){
            return new Response(ResponseConstants.USER_NOT_FOUND, null);
        }

        List<TaskComment> taskComments = taskCommentRepository.findByAuthor(user);

        return new Response(ResponseConstants.SUCCESS, taskComments);
    }

    public Response updateTaskComment(TaskCommentDTO taskCommentDTO){
        User user = userRepository.findByUserID(taskCommentDTO.getUserID()).get();
        //check if user exists
        if(user == null){
            return new Response(ResponseConstants.USER_NOT_FOUND, null);
        }

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
        TaskComment taskComment = taskCommentRepository.findByCommentID(taskCommentID).get();
        //check if task comment exists
        if(taskComment == null){
            return new Response(ResponseConstants.TASK_COMMENT_NOT_FOUND, null);
        }

        taskCommentRepository.delete(taskComment);

        return new Response(ResponseConstants.SUCCESS, null);
    }

    public Response getAuthoredComments(int userID){
        User user = userRepository.findByUserID(userID).get();
        //check if user exists
        if(user == null){
            return new Response(ResponseConstants.USER_NOT_FOUND, null);
        }

        List<TaskComment> taskComments = taskCommentRepository.findByAuthor(user);

        return new Response(ResponseConstants.SUCCESS, taskComments);
    }    

    public Response getAuthoredCommentsInTask(int userID, int taskID){
        User user = userRepository.findByUserID(userID).get();
        //check if user exists
        if(user == null){
            return new Response(ResponseConstants.USER_NOT_FOUND, null);
        }

        Task task = taskRepository.findByTaskID(taskID).get();
        //check if task exists
        if(task == null){
            return new Response(ResponseConstants.TASK_NOT_FOUND, null);
        }

        List<TaskComment> taskComments = taskCommentRepository.findByAuthorAndTask(user, task);

        return new Response(ResponseConstants.SUCCESS, taskComments);
    }
    
}
