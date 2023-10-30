import React, {useState, useEffect} from 'react';
import {useNavigate} from "react-router";
import { useParams } from 'react-router-dom';
import axios from 'axios';
import "../css/GroupDetails.css";

export default function GroupDetails(props){
    
    const navigate = useNavigate();

    sessionStorage.setItem("groupID", 1);
    sessionStorage.setItem("groupName", "test team")
    const groupID = sessionStorage.getItem("groupID");
    const groupIDInt = parseInt(groupID);
    const groupName = sessionStorage.getItem("groupName");

    const [error, setError] = useState("");
    const [newRoles, setNewRoles] = useState();
    const [usersInGroup, setUsersInGroup] = useState([]);


    useEffect(() => {
        
        if (sessionStorage.getItem("userID") === null){
            navigate("/");
        }
        axios.get("http://localhost:8080/group/listUIGInGroup", {params: {groupID: groupIDInt}})
        .then((response) => {
            setUsersInGroup(response.data);
            console.log("Users in Group:", usersInGroup);
        })
        .catch((error) => {
            setUsersInGroup([]);
        })
    }, []);

    const handleRoleChange = (userID, newRoles) => {

        axios.put("http://localhost:8080/group/updateUserPermission", null, {
        params: {groupID: groupIDInt, userID: userID, roles: newRoles}})
        .then((response) => {
            const {message} = response.data;
            if (message !== "Permission Updated Successfully"){
                setError(message);
            }
        })
        .catch((error) => { 
            setError("An unexpected error occurred!");
        })
    }

    return(
        <div className='groupDetails'>
            <div className='groupDetailsForm'>
                <h1>Group Details</h1>
                <h2>Group Name: {groupName}</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Change Role</th>
                        </tr>
                    </thead>
                    <tbody>
                    {Array.isArray(usersInGroup) ? (usersInGroup.map((user) => (
                        <tr key={user.id}>
                        <td>{user.name}</td>
                        <td>
                        <form method="post" onSubmit={(e) => handleRoleChange(groupID, user.id, e.target.value)}>
                            <select default="" value={newRoles} onChange = {(e) => setNewRoles(e.target.value)}>
                            <option value="0">Admin</option>
                            <option value="1">Manage</option>
                            <option value="2">Member</option>
                        </select>
                        <button type="submit">Save Roles</button>
                        {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                        </form> 
                        </td>
                        <td>
                        </td>
                    </tr>
                    ))
                    ) : (
                    <tr>
                        <td colSpan="3">No users in the group</td>
                    </tr>
                    )}
                    </tbody>
                </table>
                <button onClick = {() => {navigate("/home");
                        sessionStorage.setItem("groupID", null);
                        sessionStorage.setItem("groupName", null)}}>Home</button>
            </div>
        </div>
    )
}
    