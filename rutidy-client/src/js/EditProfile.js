import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Profile.css";
import axios from "axios";


export default function EditProfile(props){
    const [username, setUsername] = useState(sessionStorage.getItem("username"));
    const [password, setPassword] = useState("");
    const [cpassword, setCPassword] = useState("");
    const [name, setName] = useState(sessionStorage.getItem("name"));
    const [email, setEmail] = useState(sessionStorage.getItem("email"));
    const [error, setError] = useState("");

    const navigate = useNavigate();

    async function handleEdit(){
        if (username === "" || password === "" || name === "" || email === "") { 
            setError("Please enter all fields!");
            return;
        }
        if (password !== cpassword) {
            setError("Passwords do not match!")
            return;
        }

        axios.post("http://cs431-01.cs.rutgers.edu:8080/editprofile/user/update", {username, password, name, email}, sessionStorage.getItem("userID"))
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Account Updated!"){
                setError(message);
                return;
            }
            sessionStorage.setItem("name", user.name);
            sessionStorage.setItem("email", user.email);
            sessionStorage.setItem("username", user.username);

            navigate("/profile");
        })
        .catch((error) => { 
            setError("An unexpected error occured!");
            return;
        })
        
    }

    return (
        <div className = "profilePage">
            <div className = "profileForm">
                <h1>Edit Information</h1>
                <br></br>
                <table className="editTable">
                    <tr>
                        <td>
                            <label for="profileUsername">Username:</label>
                        </td>
                        <td className="left">
                            <input className = "profileUsername" value = {username} placeholder = "username" 
                            onChange = {(e) => setUsername(e.target.value)} ></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="profilePassword">Password:</label>
                        </td>
                        <td>
                            <input className = "profilePassword" value = {password} placeholder = "password" type = "password"
                            onChange = {(e) => setPassword(e.target.value)}></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="profileCPassword">Retype Password:</label>
                        </td>
                        <td>
                            <input className = "profileCPassword" value = {cpassword} placeholder = "retype password" type = "password"
                            onChange = {(e) => setCPassword(e.target.value)}></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="profileEmail">Email:</label>
                        </td>
                        <td>
                            <input value = {email} className = "profileEmail" placeholder = "email"
                            onChange = {(e) => setEmail(e.target.value) }></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="profileName">Name:</label>
                        </td>
                        <td>
                            <input value = {name} className = "profileName" placeholder = "name"
                            onChange = {(e) => setName(e.target.value)}></input>
                        </td>
                    </tr>
                </table>
                    <br></br>
                    {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                    <button className = "profileButton" onClick = {handleEdit}>Submit Changes</button>
                    <br></br>
                    <button className = "cancelButton" onClick = {() => navigate("/profile")}>Cancel</button>
            </div>
        </div>
    );
}