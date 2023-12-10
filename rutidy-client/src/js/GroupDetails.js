import React, {useState, useEffect} from 'react';
import {useNavigate} from "react-router";
import { useParams } from 'react-router-dom';
import axios from 'axios';
import "../css/GroupDetails.css";
import { sendEmailToGroup, sendEmailToUserInGroup } from './components/EmailComponents';
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';

import TextBox from './components/TextBox';

export default function GroupDetails(props){

    const navigate = useNavigate();

    const [error, setError] = useState("");
    const [newRoles, setNewRoles] = useState({});

    const [userRolesMap, setUserRolesMap] = useState({});

    const [newUser, setNewUser] = useState("");

    const [usersInGroup, setUsersInGroup] = useState([{userID: 1, username: "christian"}, {userID: 2, username: "peter"}]);
    const{groupID} = useParams();
    const [textEntry, setTextEntry] = useState("");
    const [group, setGroup] = useState([]);

    useEffect(() => {
        if (sessionStorage.getItem("userID") === null){
            navigate("/");
        }
        fetchUsersInGroup();
        fetchUserPermLevels();
        axios.get("http://cs431-01.cs.rutgers.edu:8080/group/get-group-by-id", {params: {"groupID": groupID}})
        .then((response) => {
            console.log(response.data);
            setGroup(response.data);
            
        })
        .catch((error) => {
            setGroup([]);
        })
    }, []);
    const handleRemoveUserClick = (event, userID) => {
        event.preventDefault();
        handleRemoveUser(userID);
    }
    
    const fetchUsersInGroup = () => {
        axios.get("http://cs431-01.cs.rutgers.edu:8080/group/listUsersInGroup", {params: {"groupID": groupID}})
        .then((response) => {
            console.log(response.data);
            setUsersInGroup(response.data);
            
        })
        .catch((error) => {
            setUsersInGroup([]);
        })
    }

    const fetchUserPermLevels = () => {
        axios.get("http://cs431-01.cs.rutgers.edu:8080/group/get-all-user-roles", {params: {"groupID": groupID}})
        .then((response) => {
            console.log(response.data);
            setUserRolesMap(response.data);

        })
        .catch((error) => {
            setUserRolesMap([]);
        })
    }




    const handleRemoveUser = (userID) => {
        axios.delete("http://cs431-01.cs.rutgers.edu:8080/group/removeUserFromGroup", {params: {userID: userID, groupID: groupID}})
        .then((response) => {
            const {message} = response.data;
            if (response.value == null){
                setError(message);
            }
            fetchUsersInGroup();
        })
        .catch((error) => {
            setError("An unexpected error occurred!");
        })
    }

    const handleRoleChangeClick = (event, userID, newRoles) => {
        event.preventDefault();
        handleRoleChange(userID, newRoles);
    }

    const handleEmailSend = (userID) => {
        sendEmailToUserInGroup(groupID, userID);
    }

    const handleAllEmailSend = () => {
        
        sendEmailToGroup(groupID);
    }

    const handleRoleChange = (userID, newRoles) => {

        axios.put("http://cs431-01.cs.rutgers.edu:8080/group/updateUserPermission", null, {params: { groupID:groupID, userID:userID, roles:newRoles}})
        .then((response) => {
            
            const {message} = response.data;
            if (message !== "Permission Updated Successfully"){
                setError(message);
            }
            fetchUsersInGroup();
            fetchUserPermLevels();
        })
        .catch((error) => { 
            setError("An unexpected error occurred!");
        })
    }

    const addUserToGroup = (textEntry) => {
        setError(null);
        axios.post("http://cs431-01.cs.rutgers.edu:8080/group/addUserToGroup", null, {params: {groupID, textEntry}})
        .then((response) => {
            const {message} = response.data;
            if (message !== "Success!"){
                setError(message);
            }
            fetchUsersInGroup();
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
                <Form.Control size = "sm" placeholder = "Add user by email/username" value = {newUser} onChange={(e) => setNewUser(e.target.value)}></Form.Control>
                <Button className = "btn-primary" onClick = {() => addUserToGroup(newUser)}>New User</Button>
                <Button className = "btn-primary" onClick = {() => handleAllEmailSend()}>Remind All</Button>
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
                            <tr key={user.userID}>
                                <td>{user.username}</td>
                                <td>
                                <form method="post" onSubmit={(e) => handleRoleChange(groupID, user.userID, e.target.value)}>
                                    <select value={newRoles[user.userID] || userRolesMap[user.userID]} onChange = {(e) => setNewRoles(prevState => ({ ...prevState, [user.userID]: e.target.value }))}>
                                    
                                    <option value={0}>Admin</option>
                                    <option value={1}>Manage</option>
                                    <option value={2}>Member</option>
                                </select>
                                </form> 
                                </td>
                                <td>
                                <Button className = "btn-primary" type = "submit" onClick={(event) => handleRoleChangeClick(event, user.userID,newRoles[user.userID])}>Save Roles</Button>
                                <Button className = "btn-danger" onClick={(event) => handleRemoveUserClick(event, user.userID)}>Remove User</Button>

                    
                                <Button className = "btn-primary" size="s" onClick= { () => handleEmailSend( user.userID)}>Send Reminder Email</Button>
                                </td>
                                <td>
                                </td>
                               
                            </tr>
                        ))}
                    
                        
                    </tbody>
                    
                </table>
            {error !== "" && <h3 className = "errorMessage">{error}</h3>}
            <Button className = "btn-primary" size = "lg" onClick = {() => navigate("/home")}>Home</Button>
            
            </div>
        </div>
    )
}
