import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Login.css";


export default function Signup(props){
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();

    async function handleSignup(){
        if (username === "" || password === "") { 
            setError("Please enter both a username and password!");
            return;
        }

        setUsername("");
        setPassword("");
    }

    return (
        <div className = "loginPage">
            <div className = "loginForm">
                <h1>Sign up</h1>
                <input className = "loginUsername" value = {username} placeholder = "username" 
                onChange = {(e) => setUsername(e.target.value)} ></input>
                <br></br>
                <input className = "loginPassword" value = {password} placeholder = "password" type = "password"
                onChange = {(e) => setPassword(e.target.value)}></input>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <p onClick = {() => navigate("/")}className = "signUp">Already have an account...login!</p>
                <button className = "loginButton" onClick = {handleSignup}>Submit!</button>
            </div>
        </div>
    );
}