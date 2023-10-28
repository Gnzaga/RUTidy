import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Login.css";
import axios from "axios";


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

        axios.post("http://localhost:8080/user/save", {username, password, name, email})
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
            <div className = "loginForm">
                <h1>Sign up</h1>
                <input className = "loginUsername" value = {username} placeholder = "username" 
                onChange = {(e) => setUsername(e.target.value)} ></input>
                <br></br>
                <input className = "loginPassword" value = {password} placeholder = "password" type = "password"
                onChange = {(e) => setPassword(e.target.value)}></input>
                <br></br>
                <input value = {email} className = "signUpEmail" placeholder = "email" onChange = {(e) => setEmail(e.target.value) }></input>
                <br></br>
                <input value = {name} className = "signUpName" placeholder = "name" onChange = {(e) => setName(e.target.value)}></input>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <p onClick = {() => navigate("/")}className = "signUp">Already have an account...login!</p>
                <button className = "loginButton" onClick = {handleSignup}>Submit!</button>
            </div>
        </div>
    );
}