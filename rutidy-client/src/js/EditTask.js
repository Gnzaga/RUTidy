import React, {useState, useEffect} from "react";
import {useNavigate} from "react-router-dom";
import { useParams } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import "../css/EditTask.css";
import axios from "axios";


export default function EditTask(props){

    const navigate = useNavigate();
    const{groupID, taskID} = useParams();


    const [taskName, setTaskName] = useState("");
    const [taskDescription, setTaskDescription] = useState("");
    const [taskDueDate, setTaskDueDate] = useState(new Date());
    const [taskPriority, setTaskPriority] = useState("");
    const [taskStatus, setTaskStatus] = useState("");

    const userID = sessionStorage.getItem("userID");
    
    const [error, setError] = useState("");
    const [usersInGroup, setUsersInGroup] = useState([]);

    useEffect(() => {
        if (sessionStorage.getItem("userID") === null){
            navigate("/");
        }
        fetchUsersInGroup();
    }, []);

    /*async function getTask(){
        axios.put("http://cs431-01.cs.rutgers.edu:8080/task/getTask", {params: {"taskID": taskID}})
        .then((response) => {
            const {message} = response.data;
            if (message !== "Success"){
                setError(message);
                return;
            }
            setTaskName(tas)
        })
        .catch((error) => {
            setError("An unexpected error occured!");
            return;
        })
    }*/

    const fetchUsersInGroup = () => {
        axios.get("http://cs431-01.cs.rutgers.edu:8080/group/listUsersInGroup", {params: {"groupID": groupID}})
        .then((response) => {
            console.log(response.data);
            setUsersInGroup(response.data);
        })
        .catch((error) => {
            setUsersInGroup([]);
        })
    }

    const handleEditTaskClick = (event) => {
        event.preventDefault();
        handleEditTask();
    }

    async function handleEditTask(){
        if (taskName === "" | taskDescription === "" | taskDueDate === "" | taskPriority === "" | taskStatus === "") { 
            setError("Please enter all fields!");
            return;
        }
        const isoDueDate = taskDueDate.toISOString();
        axios.put("http://cs431-01.cs.rutgers.edu:8080/task/update", 
        {"name":taskName, "description":taskDescription, "dueDate":isoDueDate,
        "priority":taskPriority, "status":taskStatus, "userID":userID, "groupID":groupID},
        {params:{"taskID":taskID}})
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Success"){
                setError(message);
                return;
            }
        })
        .catch((error) => { 
            setError("An unexpected error occured!");
            return;
        })
        
    }

    const handleAssignUserClick = (event, userID, taskID, username) => {
        event.preventDefault();
        handleAssignUser(userID, taskID, username);
    }

    async function handleAssignUser(userID, taskID, username){
        console.log(taskID);
        console.log(userID);
        axios.put("http://cs431-01.cs.rutgers.edu:8080/task/assign-user", null,
        {params: {taskID:taskID, userID:userID}})
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Success"){
                setError(username + " is already assigned to task");
                return;
            }
            else{
                setError(username + " has been assigned to task");
                return;
            }
        })
        .catch((error) => { 
            setError("An unexpected error occured!");
            return;
        })
    }

    const handleUnassignUserClick = (event, userID, taskID, username) => {
        event.preventDefault();
        handleUnassignUser(userID, taskID, username);
    }

    async function handleUnassignUser(userID, taskID, username){
        
        axios.put("http://cs431-01.cs.rutgers.edu:8080/task/remove-user", null, 
        {params: {"taskID":taskID, "userID":userID}})
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Success"){
                setError(username + " is not assigned to task");
                return;
            }
            else{
                setError(username + " has been unassigned from task");
                return;
            }
        })
        .catch((error) => { 
            setError("An unexpected error occured!");
            return;
        })
    }

    const handleDeleteTaskClick = (event, taskID) => {
        event.preventDefault();
        handleDeleteTask(taskID);
    }

    async function handleDeleteTask(taskID){
        
        axios.delete("http://cs431-01.cs.rutgers.edu:8080/task/delete", 
        {params: {"taskID":taskID}})
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Success"){
                setError(message);
                return;
            }
            navigate('/admin/tasks/' + (groupID || ''));
        })
        .catch((error) => { 
            setError("An unexpected error occured!");
            return;
        })
    }

    //npm install react-datepicker date-fns
    const handleDateChange = (date) => {
        setTaskDueDate(date);
    };

    return (
        <div className = "editTaskPage">
            <div className = "editTaskForm">
                <h1>Edit Task</h1>
                <input className = "taskName" value = {taskName} placeholder = "name" 
                onChange = {(e) => setTaskName(e.target.value)} ></input>
                <input className = "taskDescription" value = {taskDescription} placeholder = "description" 
                onChange = {(e) => setTaskDescription(e.target.value)} ></input>
                <DatePicker className = "taskDueDate" selected={taskDueDate} showTimeSelect timeFormat="HH:mm"
                timeIntervals={15} dateFormat="yyyy-MM-dd HH:mm:ss" onChange={handleDateChange}/>
                
                <select className = "taskStatus" value={taskStatus} onChange = {(e) => setTaskStatus(e.target.value )}>
                    <option value="" disabled selected>Select task status</option>
                    <option value={"not started"}>Not Started</option>
                    <option value={"in progress"}>In Progress</option>
                    <option value={"completed"}>Completed</option>
                </select>
                <select className = "taskPriority" value={taskPriority} onChange = {(e) => setTaskPriority(e.target.value )}>
                    <option value="" disabled selected>Select task priority</option>
                    <option value={"low"}>Low</option>
                    <option value={"normal"}>Normal</option>
                    <option value={"medium"}>Medium</option>
                    <option value={"high"}>High</option>
                </select>

                <tbody>
                    {usersInGroup.map((user) => (
                        <tr key={user.userID}>
                            <td>{user.username}</td>
                            <td>
                                <button onClick={(event) => handleAssignUserClick(event, user.userID, taskID, user.username)}>Assign User</button>
                                <button onClick={(event) => handleUnassignUserClick(event, user.userID, taskID, user.username)}>Unassign User</button>
                            </td>
                            <td>
                            </td>
                        </tr>
                    ))}
                </tbody>

                <br></br>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <button className = "button" onClick={(event) => handleEditTaskClick(event)}>Edit Task!</button>
                <button className = "button" onClick={(event) => handleDeleteTaskClick(event, taskID)}>Delete Task!</button>
                <button className = "button" onClick = {() => navigate('/admin/tasks/' + (groupID || ''))}>Back</button>
            </div>
        </div>);
}
