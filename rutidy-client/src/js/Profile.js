import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Login.css";
import axios from "axios";


export default function Profile(props){
    const [username, setUsername] = useState(sessionStorage.getItem("username"));
    const [name, setName] = useState(sessionStorage.getItem("name"));
    const [email, setEmail] = useState(sessionStorage.getItem("email"));
    const [error, setError] = useState("");

    const navigate = useNavigate();

    return (
        <div className = "profilePage">
            <h1>Sign up</h1>
                <label for="profileUsername">Username:</label>
                <input className = "profileUsername" value = {username} type="username"></input>
                <br></br>
                <label for="profileEmail">Email:</label>
                <input value = {email} className = "profileEmail" type = "email"></input>
                <br></br>
                <label for="profileName">Name:</label>
                <input value = {name} className = "profileName" placeholder = "name"></input>
                <p onClick = {() => navigate("/")}className = "editProfile">Click here to edit profile information or set a new password!</p>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
        </div>
    );
}