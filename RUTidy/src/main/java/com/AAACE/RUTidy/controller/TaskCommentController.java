package com.AAACE.RUTidy.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.AAACE.RUTidy.constants.ResponseConstants;




import com.AAACE.RUTidy.dto.*;
import com.AAACE.RUTidy.model.*;
import com.AAACE.RUTidy.service.task.*;


@RestController
@CrossOrigin
@RequestMapping("/comment")
public class TaskCommentController {
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
        return taskCommentService.createTaskComment(taskCommentDTO);
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
        return taskCommentService.deleteTaskComment(taskCommentID);
    }

    /**
     * This will return a sorted list of all the taskComments for a task
     */
    @GetMapping("/get/sorted")
    public Response getSortedTaskComments(@RequestParam int taskID) {
        return taskCommentService.getTaskCommentsSorted(taskID);
    }




    
}
