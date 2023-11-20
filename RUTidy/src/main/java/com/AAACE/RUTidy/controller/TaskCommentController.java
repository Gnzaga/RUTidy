package com.AAACE.RUTidy.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.AAACE.RUTidy.constants.ResponseConstants;





import com.AAACE.RUTidy.dto.*;
import com.AAACE.RUTidy.model.*;
import com.AAACE.RUTidy.service.task.*;


@RestController
@CrossOrigin
@RequestMapping("/comment")
public class TaskCommentController {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private TaskCommentService taskCommentService;

    /**
     * This will create a new task comment.
     * 
     * The taskCommentDTO requires the following fields:
     * - taskCommentText
     * - taskCommentTaskID - this is the task the comment is being assigned to
     * - taskCommentUserID - this is the user being initially assigned the comment
     * 
     * @param taskCommentDTO
     * @return
     */
    @PostMapping("/create")
    public Response createTaskComment(@RequestBody TaskCommentDTO taskCommentDTO) {
        Response response = taskCommentService.createTaskComment(taskCommentDTO);
        if (response.getMessage() == ResponseConstants.SUCCESS) {
            TaskComment taskComment = (TaskComment) response.getObject();
            sendCommentToClients(taskComment);
        }
        return response;

        //return taskCommentService.createTaskComment(taskCommentDTO);
    }

    @GetMapping("/stream")
    public SseEmitter stream() throws IOException   {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // Keep the connection open indefinitely
        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> {
            emitter.complete();
            this.emitters.remove(emitter);
        });

    return emitter;
    }

    public void sendCommentToClients(TaskComment comment) {
    List<SseEmitter> deadEmitters = new ArrayList<>();
    this.emitters.forEach(emitter -> {
        try {
            emitter.send(comment);
        } catch (Exception e) {
            deadEmitters.add(emitter);
        }
    });
    this.emitters.removeAll(deadEmitters);
}


    public void sendCommentToClietns(TaskComment comment){
           List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(comment);
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });
        this.emitters.removeAll(deadEmitters);
    }
    

    /**
     * This will get a task comment by its ID.
     * 
     * @param taskCommentID
     * @return
     */
    @GetMapping("/get")
    public Response getTaskComment(@RequestParam int taskCommentID) {
        return taskCommentService.getTaskComment(taskCommentID);
    }

    /**
     * This will get all task comments in a task.
     * 
     * @param taskID
     * @return 
     */
    @GetMapping("/get/all")
    public Response getAllTaskComments(@RequestParam int taskID) {
        return taskCommentService.getTaskComments(taskID);
    }

    /**
     * This will update a task comment.
     * 
     * The taskCommentDTO requires the following fields:
     * - taskCommentID
     * - taskCommentText
     * - taskCommentTaskID - this is the task the comment is being assigned to
     * - taskCommentUserID - this is the user being initially assigned the comment
     * 
     * @param taskCommentDTO
     * @return
     */
    @PutMapping("/update")
    public Response updateTaskComment(@RequestBody TaskCommentDTO taskCommentDTO) {
        return taskCommentService.updateTaskComment(taskCommentDTO);
    }

    /**
     * This will delete a task comment.
     * 
     * @param taskCommentID
     * @return
     */
    @DeleteMapping("/delete")
    public Response deleteTaskComment(@RequestParam int taskCommentID) {
        Response response = taskCommentService.deleteTaskComment(taskCommentID);
        if (response.getMessage() == ResponseConstants.SUCCESS) {
            TaskComment taskComment = (TaskComment) response.getObject();
            sendCommentToClients(taskComment);
        }
        return response;
    }

    /**
     * This will return a sorted list of all the taskComments for a task
     */
    @GetMapping("/get/sorted")
    public Response getSortedTaskComments(@RequestParam int taskID) {
        return taskCommentService.getTaskCommentsSorted(taskID);
    }




    
}
