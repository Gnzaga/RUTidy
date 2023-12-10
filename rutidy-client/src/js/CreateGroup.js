import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/CreateGroup.css";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';


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
        axios.post("http://cs431-01.cs.rutgers.edu:8080/group/create", {"name":name, "ownerID": userID})
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Group has been created!"){
                setError(message);
                return;
            }
            //navigate("");
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
                <Form.Control size = "lg" value = {name} onChange = {(e) => setName(e.target.value)} placeholder = "group name"></Form.Control>
                <br></br>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <Button onClick = {handleCreateGroup} className = "btn-primary">Create Group!</Button>
                <br></br>
                <Button style = {{marginTop: "2rem"}}onClick = {() => navigate("/home")} className = "btn-secondary">Home</Button>
            </div>
        </div>    );
}