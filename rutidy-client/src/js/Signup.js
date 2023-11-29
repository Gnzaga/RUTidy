import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Login.css";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';


export default function Signup(props){
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();

    async function handleSignup(){
        if (username === "" || password === "" || name === "" || email === "") { 
            setError("Please enter all fields!");
            return;
        }

        axios.post("http://cs431-01.cs.rutgers.edu:8080/user/save", {username, password, name, email})
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Account Created!"){
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
        <div className = "loginPage">
            <div className = "loginForm signupForm">
                <h1>Sign up</h1>
                <Form.Control className = "signUpInput" size = "sm" onChange = {(e => setUsername(e.target.value))} placeholder = "username" value = {username}></Form.Control>
                <br></br>
                <Form.Control className = "signUpInput" size = "sm" onChange = {(e => setPassword(e.target.value))} placeholder = "password" value = {password}></Form.Control>
                <br></br>
                <Form.Control className = "signUpInput" size = "sm" onChange = {(e => setEmail(e.target.value))} placeholder = "email" value = {email}></Form.Control>
                <br></br>
                <Form.Control style = {{marginBottom: "0px"}} className = "signUpInput" size = "sm" onChange = {(e => setName(e.target.value))} placeholder = "name" value = {name}></Form.Control>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <Button onClick = {() => navigate("/")} className = "btn-link">Already have an account... login!</Button>
                <br></br>
                <Button style = {{marginTop: "1rem"}} onClick = {handleSignup} className = "btn-primary">Submit!</Button>
            </div>
        </div>
    );
}