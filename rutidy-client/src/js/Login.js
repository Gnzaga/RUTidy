import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import "../css/Login.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';


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
            sessionStorage.setItem("username", user.username);
            sessionStorage.setItem("userID", user.userID);

            setError("");
            setUsername("");
            setPassword("");
            navigate("/home");

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
                <Form.Control className = "loginInput" size = "sm" onChange = {(e => setUsername(e.target.value))} placeholder = "username" value = {username}></Form.Control>
                <Form.Control className = "loginInput" size = "sm" onChange = {(e) => setPassword(e.target.value)} placeholder = "password" type = "password" value = {password}></Form.Control>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <Button onClick = {() => navigate("/signup")} className = "btn-link">Don't have an account... signup!</Button>
                <br></br>
                <Button style = {{marginTop: "1rem"}} className = "btn-primary" onClick = {handleLogin}>Submit</Button>
            </div>
        </div>
    );
}