import React, {useState, useEffect} from 'react';
import {useNavigate} from "react-router";
import { useParams } from 'react-router-dom';
import axios from 'axios';
import "../css/GroupDetails.css";

export default function GroupDetails(props){
    
    const navigate = useNavigate();

    const groupID = sessionStorage.getItem("groupID");
    const groupIDInt = parseInt(groupID);
    const groupName = sessionStorage.getItem("groupName");

    const [error, setError] = useState("");

    const [newRoles, setNewRoles] = useState([]);

    const [usersInGroup, setUsersInGroup] = useState([]);


    function queryUserInGroups(){
        axios.get("http://localhost:8080/group/listUIGInGroup", {params: {groupID: groupIDInt}})
        .then((response) => {
            const usersInGroups = response.data;
            setUsersInGroup(usersInGroups);

            console.log("Users in Group:", usersInGroup);
        })
        .catch((error) => {
            setUsersInGroup([]);
        })
    }

    useEffect(() => {
        if (sessionStorage.getItem("userID") === null){
            navigate("/");
        }
        queryUserInGroups();
    }, []);
        
    const updateRoleLocally = (userID, newRole) => {
        // Update the role in your local state.
        const updatedUsers = usersInGroup.map(user => {
          if (user.user.userID === userID) {
            return { ...user, roles: newRole };
          }
          return user;
        });
        setUsersInGroup(updatedUsers);
      };

    const handleRoleChange = (userID, newRoles) => {
        const id = userID;

        axios.put("http://localhost:8080/group/updateUserPermission", null, {
        params: {groupID: groupIDInt, userID: id, roles: newRoles}})
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
                            <th className='userColumn'>Name</th>
                            <th className='roleColumn'>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                    {usersInGroup.map((user, index) => (
                        <tr key={index}>
                        <td className='userColumn'>{user.user.name}</td>
                        <td className='roleColumn'>{user.roles}</td>
                        <td className='saveRoles'>
                            <p onClick={() => {
                                handleRoleChange(user.user.userID, 0);
                                updateRoleLocally(user.user.userID, 0); 
                            }}>Make Admin</p>
                            <p onClick={() => {
                                handleRoleChange(user.user.userID, 1);
                                updateRoleLocally(user.user.userID, 1); 
                            }}>Make Manager</p>
                            <p onClick={() => {
                                handleRoleChange(user.user.userID, 2);
                                updateRoleLocally(user.user.userID, 2); 
                            }}>Make Member</p>
                        </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <button onClick = {() => {navigate("/home");
                        sessionStorage.setItem("groupID", null);
                        sessionStorage.setItem("groupName", null)}}>Home</button>
            </div>
        </div>
    )
}
    