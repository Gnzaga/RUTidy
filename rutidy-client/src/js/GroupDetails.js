import React, {useState, useEffect} from 'react';
import {useNavigate} from "react-router";
import { useParams } from 'react-router-dom';
import axios from 'axios';
import "../css/GroupDetails.css";

export default function GroupDetails(props){

    const navigate = useNavigate();

    const [error, setError] = useState("");
    const [newRoles, setNewRoles] = useState("");

    const [usersInGroup, setUsersInGroup] = useState([]);
    const{groupID} = useParams();
    const [group, setGroup] = useState([]);

    useEffect(() => {
        if (sessionStorage.getItem("userID") === null){
            navigate("/");
        }
        axios.get("http://localhost:8080/group/listUsersInGroup", null, {params: {groupID}})
        .then((response) => {
            setUsersInGroup(response.data);
        })
        .catch((error) => {
            setUsersInGroup([]);
        })
    }, []);

    const handleRoleChange = (userID, newRoles) => {

        axios.put("http://localhost:8080/group/updateUserPermission", null, {params: {groupID, userID, newRoles}})
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
                <h2>Group Name: {group.name}</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Change Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        {usersInGroup.map((user) => (
                            <tr key={user.id}>
                                <td>{user.name}</td>
                                <td>
                                <form method="post" onSubmit={(e) => handleRoleChange(groupID, user.id, e.target.value)}>
                                    <select value={newRoles} onChange = {(e) => setNewRoles(e.target.value)}>
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
                        ))}
                    </tbody>
                </table>
                <button onClick = {() => navigate("/home")}>Home</button>
            </div>
        </div>
    )
}
    