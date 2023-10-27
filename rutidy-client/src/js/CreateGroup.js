
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/CreateGroup.css";
import axios from "axios";


export default function Create(props){
    const [name, setName] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();

    async function handleCreateGroup(){
        if (name === "") { 
            setError("Please enter a name for your server!");
            return;
        }
        const userID = sessionStorage.getItem("userID");
        axios.post("http://localhost:8080/group/create", {"name":name, "ownerID": userID})
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Group has been created!"){
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

    return (
        <div className = "createGroupPage">
            <div className = "createGroupForm">
                <h1>Create Group</h1>
                <input className = "groupName" value = {name} placeholder = "Enter group name" 
                onChange = {(e) => setName(e.target.value)} ></input>
                <br></br>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <button className = "createButton" onClick = {handleCreateGroup}>Create Group!</button>
                <button className = "goHome" onClick = {() => navigate("/home")}>home</button>
            </div>
        </div>    );
}