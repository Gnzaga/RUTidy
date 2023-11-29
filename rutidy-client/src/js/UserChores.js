import React, {useEffect, useState} from "react";
import {useLocation, useNavigate, useResolvedPath, useSearchParams} from "react-router-dom";
import "../css/Chores.css";
import Check from "../image/check.png"
import X from "../image/x.png"
import axios from "axios";
import TaskCommentsComponent from "./components/TaskComment";
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';


export default function UserChores(props){
    const [chores, setChores] = useState([{"name": "dishes", "status": "completed", "duedate": "", "description": "do dishes"}, {"name": "laundry", "status": "incomplete", "duedate": "", "description": "do laundry"}]);
    const [displayChore, setDisplayChore] = useState({"name": "", "status": "", "duedate": "", "description": ""});
    const [status, setStatus] = useState("completed");
    const location = useLocation();
    const path = location.pathname.split("/");

    async function  displayDetails(taskID){
        setDisplayChore(chores.find((chore) => chore.taskID === taskID));
        setStatus(chores.find((chore) => chore.taskID === taskID).status);
    }

    useEffect(() => {
        console.log(path[path.length-1]);
        populateChores()
    }, []);

    async function populateChores(){
        axios.get("http://cs431-01.cs.rutgers.edu:8080/task/get-group-tasks", 
        {params: { "groupID": path[path.length-1], userTimeZone: Intl.DateTimeFormat().resolvedOptions().timeZone}})
        .then((response) => { 
            const {message, object} = response.data;
            setChores(object);
            setDisplayChore(object[0]);
        }).catch((error) => {
            console.log(error);
        });
    }

    const navigate = useNavigate();

    async function  changeChoreStatus(taskID){
        axios.put("http://cs431-01.cs.rutgers.edu:8080/task/update-status?taskID=" + taskID + "&taskStatus=" + status + "&userID=" + sessionStorage.getItem("userID"))
        .then((response) => { 
            console.log(response.data.message);
        }).catch((error) => {
            console.log(error);
        });

        let tempChores = chores;
        tempChores[tempChores.findIndex((chore) => chore.taskID === taskID)].status = status;
        setChores(tempChores);

        navigate(location.pathname);
    }

    return (
        <div className = "choresPage">
            <div className = "leftPane">
                <div className = "listView">
                    <br></br>
                    <h1>List of Chores</h1>
                    <table className="choreTable">{chores.map((chore) =>
                        <tr>
                            <td><img className="statusImage" src = {chore.status.toLowerCase() === "completed"? Check: X}></img></td>
                            <td><h2 className="choreItem" onClick={()=>displayDetails(chore.taskID)}>{chore.name}</h2></td>
                        </tr>
                    )}
                    </table>
                </div>
            </div>
            <Button size = "sm" className = "btn-secondary" onClick={()=>navigate("/home")}>Return</Button>
            <Button size = "sm" className = "btn-primary" onClick={()=>navigate("/create/nonscheduledtask/"+ path[path.length-1])}>Create NS task</Button>
            <div className = "rightPane">
                <div className = "detailView">
                    <br></br>
                    <h1>Chore Details</h1>
                    <table>
                        <tr>
                            <td><h2 className="choreInfoType">Name: </h2></td>
                            <td><h2 className="choreInfo">{displayChore.name}</h2></td>
                            
                        </tr>
                        <tr>
                            <td>
                                <h2 className="choreInfoType">Status: </h2>
                                <Button className = "btn-primary" onClick={()=>changeChoreStatus(displayChore.taskID)}>Mark Task As</Button>
                            </td>
                            <td>
                                <h2 className="choreInfo">{displayChore.status}</h2>
                                <select value={status} onChange = {(e) => setStatus(e.target.value)}>
                                    <option value={"not started"}>Not Started</option>
                                    <option value={"in progress"}>In Progress</option>
                                    <option value={"completed"}>Completed</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><h2 className="choreInfoType">Due Date: </h2></td>
                            <td><h2 className="choreInfo">{displayChore.dueDate}</h2></td>
                        </tr>
                        <tr>
                            <td><h2 className="choreInfoType">Description: </h2></td>
                        </tr>
                    </table>
                    <div>
                        <h2 className="choreDescription">{displayChore.description}</h2>
                    </div>
                    <div className="task-comments-container">
                    <TaskCommentsComponent taskID={displayChore.taskID} 
                    currentUserID = {sessionStorage.getItem("userID")} />
                    </div>
                    <td><h2 className="choreInfo">Assigned to: {displayChore.assignedUsers?.map(user => user.name).join(', ')}</h2></td>
                </div>
             
            </div>
        </div>
    );
}
