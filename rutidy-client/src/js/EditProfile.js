import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Profile.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';


export default function EditProfile(props){
    const [username, setUsername] = useState(sessionStorage.getItem("username"));
    const [password, setPassword] = useState("");
    const [cpassword, setCPassword] = useState("");
    const [name, setName] = useState(sessionStorage.getItem("name"));
    const [email, setEmail] = useState(sessionStorage.getItem("email"));
    const [error, setError] = useState("");

    const navigate = useNavigate();

    async function handleEdit(){
        if (username === "" || name === "" || email === "") { 
            setError("Please enter all fields!");
            return;
        }
        if (password !== cpassword) {
            setError("Passwords do not match!")
            return;
        }

        axios.post("http://cs431-01.cs.rutgers.edu:8080/user/update?userID=" + sessionStorage.getItem("userID").toString(), {username, password, name, email})
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Account Updated!"){
                setError(message);
                return;
            }
            sessionStorage.setItem("name", name);
            sessionStorage.setItem("email", email);
            sessionStorage.setItem("username", username);

            navigate("/profile");
        })
        .catch((error) => { 
            setError("An unexpected error has occurred!");
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
                            <Form.Control onChange = {(e) => setUsername(e.target.value)} size = "sm" type = "text" placeholder = "" value = {username}></Form.Control>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="profilePassword">Password:</label>
                        </td>
                        <td>
                            <Form.Control onChange = {(e) => setPassword(e.target.value)} size = "sm" type = "text" placeholder = "" value = {password}></Form.Control>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="profileCPassword">Retype Password:</label>
                        </td>
                        <td>
                            <Form.Control onChange = {(e) => setCPassword(e.target.value)} size = "sm" type = "password" value = {cpassword}></Form.Control>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="profileEmail">Email:</label>
                        </td>
                        <td>
                            <Form.Control onChange = {(e) => setEmail(e.target.value)} size = "sm" type = "text" value = {email}></Form.Control>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="profileName">Name:</label>
                        </td>
                        <td>
                            <Form.Control onChange = {(e) => setName(e.target.value)} size = "sm" type = "text" value = {name}></Form.Control>
                        </td>
                    </tr>
                </table>
                    <br></br>
                    {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                    <Button onClick = {handleEdit} className = "btn-primary">Submit Changes</Button>{' '}
                    <br></br>
                    <Button style = {{marginTop: "1rem"}} size = "sm" className = "btn-secondary" onClick = {() => navigate("/profile")}>Cancel</Button>
            </div>
        </div>
    );
}