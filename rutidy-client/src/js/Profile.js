import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Profile.css";
import axios from "axios";


export default function Profile(props){
    const [username, setUsername] = useState(sessionStorage.getItem("username"));
    const [name, setName] = useState(sessionStorage.getItem("name"));
    const [email, setEmail] = useState(sessionStorage.getItem("email"));
    const [error, setError] = useState("");

    const navigate = useNavigate();

    return (
        <div className = "profilePage">
            <div className = "profileForm">
            <h1>Profile Information</h1>
            <br></br>
            <table className="profileTable">
                    <tr>
                        <td className="profileTD">
                            <label>Username:</label>
                        </td>
                        <td className="profileTDLeft">
                            <label>{username}</label>
                        </td>
                    </tr>
                    <tr>
                        <td className="profileTD">
                            <label>Email:</label>
                        </td>
                        <td className="profileTDLeft">
                            <label>{email}</label>
                        </td>
                    </tr>
                    <tr>
                        <td className="profileTD">
                            <label>Name:</label>
                        </td>
                        <td className="profileTDLeft">
                            <label>{name}</label>
                        </td>
                    </tr>
                </table>
                <br></br>
                <p onClick = {() => navigate("/profile/edit")}className = "editProfile">Click here to edit profile information or set a new password!</p>
                {error !== "" && <h3 className = "errorMessage">{error}</h3>}
            </div>
        </div>
    );
}