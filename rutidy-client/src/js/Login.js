import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
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
        
        axios.post("http://cs431-01.cs.rutgers.edu:8080/user/login", {"email": username, password})
        .then((response) => {
            const {message, user} = response.data;
            if (message !== "Login successful") { 
                setError(message);
                return;
            }

            sessionStorage.setItem("name", user.name);
            sessionStorage.setItem("email", user.email);
            sessionStorage.setItem("username", user.username)
            sessionStorage.setItem("userID", user.userID);

            setError("");
            setUsername("");
            setPassword("");
            navigate("/profile");

            console.log("name: " + sessionStorage.getItem("name"));
        })
        .catch((error) => {
            setError("An unexpecter error occured!");
            return;
        })
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