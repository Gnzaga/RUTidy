import React, {useState, useEffect} from "react";
import "../css/AdminTasks.css";
import axios from "axios";
import X from "../image/x.png";
import Edit from "../image/edit.png";
import { useParams } from 'react-router-dom';
import {useNavigate} from "react-router";
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';

/**
 * Components the represents the page for admins of groups to manage tasks
 * @param {*} props 
 * @returns component for admin tasks
 */
export default function AdminTasks(props){
    const [displayTask, setDisplayTask] = useState(false);
    const [tasks, setTasks] = useState([
        {
            "name": "Task Name",
            "description": "Task Description",
            "assignedDate": "2023-11-03T17:34:44.592447",
            "dueDate": "2023-11-27T14:30:00",
            "priority": "high",
            "status": "complete",
            "taskID": 1,
            "assignedUsers": [
                {
                    "name": "Christian",
                    "email": "t",
                    "password": "$2a$10$HdQ55FCbc/rNpcKRzaUQp.LtSjAdxBlWmd4viWKe1pokCLgi5c2Dy",
                    "username": null,
                    "userID": 1
                },
                {
                    "name": "Peter",
                    "email": "d",
                    "password": "$2a$10$yyUYW/bHZyhB8MG85EbyQedq/c2JY9AzRfPTVC8Z8LMsX6UMms8b6",
                    "username": "d",
                    "userID": 2
                }
            ],
            "group": {
                "groupID": 1,
                "name": "test",
                "owner": {
                    "name": null,
                    "email": "t",
                    "password": "$2a$10$HdQ55FCbc/rNpcKRzaUQp.LtSjAdxBlWmd4viWKe1pokCLgi5c2Dy",
                    "username": null,
                    "userID": 1
                }
            }
        },
        {
            "name": "Task Name",
            "description": "Task Description",
            "assignedDate": "2023-11-03T17:42:06.486806",
            "dueDate": "2023-11-15T14:30:00",
            "priority": "High",
            "status": "In Progress",
            "taskID": 2,
            "assignedUsers": [
                {
                    "name": "Anthony",
                    "email": "t",
                    "password": "$2a$10$HdQ55FCbc/rNpcKRzaUQp.LtSjAdxBlWmd4viWKe1pokCLgi5c2Dy",
                    "username": null,
                    "userID": 1
                },
                {
                    "name": "Jakob",
                    "email": "d",
                    "password": "$2a$10$yyUYW/bHZyhB8MG85EbyQedq/c2JY9AzRfPTVC8Z8LMsX6UMms8b6",
                    "username": "d",
                    "userID": 2
                }
            ],
            "group": {
                "groupID": 1,
                "name": "test",
                "owner": {
                    "name": null,
                    "email": "t",
                    "password": "$2a$10$HdQ55FCbc/rNpcKRzaUQp.LtSjAdxBlWmd4viWKe1pokCLgi5c2Dy",
                    "username": null,
                    "userID": 1
                }
            }
        }
    ]);
    const [selectedTask, setSelectedTask] = useState({});
    const {groupID} = useParams();
    const navigate = useNavigate();

    /**
     * Function called after render that queries tasks in the managed group
     */
    useEffect(() => {
        if (sessionStorage.getItem("userID") == null) navigate("/home");

        axios.get("http://cs431-01.cs.rutgers.edu:8080/task/get-group-tasks", {params: {groupID: groupID}})
        .then((response) => {
            setTasks(response.data.object);
        })
        .catch((error) => {
            navigate("/home")
        })

    }, [])

    return (
        <div className = "adminTasksPage">
            <Button onClick = {() => navigate("/home")} className = "btn-secondary">Return to home</Button>
            <Button className = "btn-primary" onClick = {() => navigate(`/create/task/${groupID}`)}>Create a task</Button>
            <div className = "adminTasksContainer">
                <div className = "adminTasksDisplay">
                    <h1>Group Tasks</h1>
                    <div className = "groupTasks">
                        {tasks.map((task) => {
                            return (
                                <div className = "adminTask">
                                    <div className = "adminTaskLeftDiv">
                                        <img src = {Edit} alt = "editImage" onClick = {() => navigate('/edit/task/' + (groupID) + '/' + (task.taskID))}></img>
                                        <h3 onClick = {() => {setSelectedTask(task); setDisplayTask(true)}}>{task.name}</h3>
                                    </div>
                                    <h3>{task.assignedUsers.map((user) => user.name).join(",")}</h3>
                                </div>
                            )
                        })}
                    </div>
                </div>
                {displayTask && <div className = "singleTask">
                    <h3>{selectedTask.name}</h3>
                    <h4>Descrpition: {selectedTask.description}</h4>
                    <h4>Assigned Date: {selectedTask.assignedDate.substring(0, 10)}</h4>
                    <h4>Due Date: {selectedTask.dueDate.substring(0, 10)}</h4>
                    <h4>Priority: {selectedTask.priority}</h4>
                    <h4>Status: {selectedTask.status}</h4>
                    <img onClick = {() => {setDisplayTask(false); setSelectedTask({})}} src = {X} alt = "errorImage"></img>
                </div>}
            </div>
        </div>
    )

};