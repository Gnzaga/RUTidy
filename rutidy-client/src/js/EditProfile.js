import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Login.css";
import axios from "axios";


export default function EditProfile(props){
    const [username, setUsername] = useState(sessionStorage.getItem("username"));
    const [password, setPassword] = useState("");
    const [name, setName] = useState(sessionStorage.getItem("name"));
    const [email, setEmail] = useState(sessionStorage.getItem("email"));
    const [error, setError] = useState("");

    const navigate = useNavigate();

    async function handleEdit(){
        if (username === "" || password === "" || name === "" || email === "") { 
            setError("Please enter all fields!");
            return;
        }

        axios.post("http://localhost:8080/editprofile/user/save", {username, password, name, email})
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
        <div className = "profilePage">
            <div className = "profileForm">
                <h1>Sign up</h1>
                <input className = "profileUsername" value = {username} placeholder = "username" 
                onChange = {(e) => setUsername(e.target.value)} ></input>
                <br></br>
                <input className = "profilePassword" value = {password} placeholder = "password" type = "password"
                onChange = {(e) => setPassword(e.target.value)}></input>
                <br></br>
                <input value = {email} className = "signUpEmail" placeholder = "email" onChange = {(e) => setEmail(e.target.value) }></input>
                <br></br>
                <input value = {name} className = "signUpName" placeholder = "name" onChange = {(e) => setName(e.target.value)}></input>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                <p onClick = {() => navigate("/")}className = "signUp">Already have an account...login!</p>
                <button className = "editButton" onClick = {handleEdit}>Submit changes!</button>
                <br></br>
                <br></br>
                <button className = "cancel" onClick = {() => navigate("/profile")}>Submit changes!</button>
            </div>
        </div>
    );
}