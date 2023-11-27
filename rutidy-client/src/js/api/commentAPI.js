import React, {useState, useEffect} from "react";

const COMMENT_API_BASE_URL = "http://cs431-01.cs.rutgers.edu:8080/comment";



export async function createTaskComment(taskCommentDTO) {
    const response = await fetch(`${COMMENT_API_BASE_URL}/create`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(taskCommentDTO)
    });
    return response.json();
}


export async function getTaskComment(taskCommentID) {
    const response = await fetch(`${COMMENT_API_BASE_URL}/get?taskCommentID=${taskCommentID}`);
    return response.json();
}



export async function getAllTaskComments(taskID) {
    const response = await fetch(`${COMMENT_API_BASE_URL}/get/all?taskID=${taskID}`);
    return response.json();
}


export async function updateTaskComment(taskCommentDTO) {
    const response = await fetch(`${COMMENT_API_BASE_URL}/update`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(taskCommentDTO)
    });
    return response.json();
}


export async function deleteTaskComment(taskCommentID) {
    const response = await fetch(`${COMMENT_API_BASE_URL}/delete?taskCommentID=${taskCommentID}`, {
        method: 'DELETE'
    });
    return response.json();
}


export async function getSortedTaskComments(taskID) {
    const response = await fetch(`${COMMENT_API_BASE_URL}/get/sorted?taskID=${taskID}`);
    return response.json();
}


export const buildCommentDTO = (taskID, userID, comment) => {
    return {
        comment: comment,
        userID: userID,
        taskID: taskID
    }
}

export const buildCommentUpdateDTO = (comment, userID, taskID, taskCommentID) => {
    return {
        taskID: taskID,
        userID: userID,
        taskCommentID: taskCommentID,
        comment: comment
    }
}

