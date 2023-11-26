import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import { useParams } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import "../css/CreateTask.css";
import axios from "axios";


export default function CreateNSTask(props){
    const [taskName, setTaskName] = useState("");
    const [taskDescription, setTaskDescription] = useState("");
    const [taskDueDate, setTaskDueDate] = useState(new Date());
    const [taskPriority, setTaskPriority] = useState("N/A");

    const{groupID} = useParams();
    const userID = sessionStorage.getItem("userID");
    const initialStatus = "Completed";

    const [error, setError] = useState("");

    const navigate = useNavigate();


    
    const handleCreateTaskClick = (event) => {
        event.preventDefault();
        handleCreateTask();
    }


    async function handleCreateTask(){
        if (taskName === "" | taskDescription === "") { 
            setError("Please enter all fields!");
            return;
        }
        const isoDueDate = taskDueDate.toISOString();
        setTaskPriority("N/A");
        axios.post("http://cs431-01.cs.rutgers.edu:8080/task/create", 
        {"name":taskName, "description":taskDescription, "dueDate":isoDueDate,
        "priority":taskPriority, "status":initialStatus,"userID":userID, "groupID":groupID})
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Task created successfully"){
                setError(message);
                return;
            }
            navigate("/");
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
        <div className = "createTaskPage">
            <div className = "createTaskForm">
                <h1>Create a Non-Scheduled Task</h1>
                <input className = "taskName" value = {taskName} placeholder = "Enter task name" 
                onChange = {(e) => setTaskName(e.target.value)} ></input>
                <input className = "taskDescription" value = {taskDescription} placeholder = "Enter task description" 
                onChange = {(e) => setTaskDescription(e.target.value)} ></input>
                
                <br></br>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <button className = "createButton" onClick={(event) => handleCreateTaskClick(event)}>Create Task!</button>
                <br></br>
                <button className = "button" onClick = {() => navigate('/chores/' + (groupID || ''))}>Back</button>
            </div>
        </div>);
}
