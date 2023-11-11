import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import { useParams } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { format } from 'date-fns';
import "../css/CreateTask.css";
import axios from "axios";


export default function CreateTask(props){
    const [taskName, setTaskName] = useState("");
    const [taskDescription, setTaskDescription] = useState("");
    const [taskDueDate, setTaskDueDate] = useState(new Date());
    const [taskPriority, setTaskPriority] = useState("");

    const{groupID} = useParams();
    const userID = sessionStorage.getItem("userID");
    const initialStatus = "not started";

    const [error, setError] = useState("");

    const navigate = useNavigate();

    const handleCreateTaskClick = (event) => {
        event.preventDefault();
        handleCreateTask();
    }

    async function handleCreateTask(){
        if (taskName === "" | taskDescription === "" | taskDueDate === "" | taskPriority === "") { 
            setError("Please enter all fields!");
            return;
        }

        const timezoneOffset = taskDueDate.getTimezoneOffset();
        const adjustedIsoDueDate = new Date(taskDueDate.getTime() - (timezoneOffset * 60 * 1000)).toISOString().replace(/\.\d+/, '');
        axios.post("http://localhost:8080/task/create", 
        {"name":taskName, "description":taskDescription, "dueDate":adjustedIsoDueDate,
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
                <h1>Create Task</h1>
                <input className = "taskName" value = {taskName} placeholder = "Enter task name" 
                onChange = {(e) => setTaskName(e.target.value)} ></input>
                <input className = "taskDescription" value = {taskDescription} placeholder = "Enter task description" 
                onChange = {(e) => setTaskDescription(e.target.value)} ></input>
                <DatePicker selected={taskDueDate} showTimeSelect timeFormat="HH:mm"
                timeIntervals={15} dateFormat="yyyy-MM-dd HH:mm:ss" onChange={handleDateChange}/>
                <select value={taskPriority} onChange = {(e) => setTaskPriority(e.target.value )}>
                    <option value="" disabled selected>Select task priority</option>
                    <option value={"low"}>Low</option>
                    <option value={"normal"}>Normal</option>
                    <option value={"medium"}>Medium</option>
                    <option value={"high"}>High</option>
                </select>
                <br></br>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <button className = "createButton" onClick={(event) => handleCreateTaskClick(event)}>Create Task!</button>
                <br></br>
                <button className = "button" onClick = {() => navigate('/admin/tasks/' + (groupID || ''))}>Back</button>
            </div>
        </div>);
}