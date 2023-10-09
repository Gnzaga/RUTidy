import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Login.css";


export default function Login(props){
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();

    async function handleLogin(){
        if (username === "" || password === "") { 
            setError("Please enter both a username and password!");
            return;
        }
        const response = await fetch("http://localhost:8080/user/login", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({username, password})
        });
        setUsername("");
        setPassword("");
    }

    return (
        <div className = "loginPage">
            <div className = "loginForm">
                <h1>Login</h1>
                <input className = "loginUsername" value = {username} placeholder = "username" 
                onChange = {(e) => setUsername(e.target.value)} ></input>
                <br></br>
                <input className = "loginPassword" value = {password} placeholder = "password" type = "password"
                onChange = {(e) => setPassword(e.target.value)}></input>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <p onClick = {() => navigate("/signup")}className = "signUp">Don't have an account... signup!</p>
                <button className = "loginButton" onClick = {handleLogin}>Submit!</button>
            </div>
        </div>
    );
}