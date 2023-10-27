import React, {useState, useEffect} from 'react';
import axios from 'axios';
import "../css/GroupDetails.css";

export default function GroupDetails(props){
    const [error, setError] = useState("");
    const [newRoles, setNewRoles] = useState("");
    const [currentRole, setCurrentRole] = useState("");

    const [userInGroup, setuserInGroup] = useState("");
    const [group, setGroup] = useState("");

    const handleRoleChange = (UIGroupID, newRoles) => {

        axios.put("http://localhost:8080/group/updateUserPermission", null, {params: {UIGroupID, newRoles}})
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
                            {currentRole === 0 && <th>Change Role</th>}
                        </tr>
                    </thead>
                    <tbody>
                        {userInGroup.map((user) => (
                            <tr key={user.id}>
                                <td>{user.name}</td>
                                <td>
                                {currentRole === 0 && (
                                    <form method="post" onSubmit={(e) => handleRoleChange(user.id, e.target.value)}>
                                        <select value={newRoles} onChange = {(e) => setNewRoles(e.target.value)}>
                                        <option value="0">Admin</option>
                                        <option value="1">Manage</option>
                                        <option value="2">Member</option>
                                    </select>
                                    <button type="submit">Save Roles</button>
                                    {error !== "" && <h3 className = "errorMessage">{error}</h3>}
                                    </form>  
                                )}
                                </td>
                                <td>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}
    