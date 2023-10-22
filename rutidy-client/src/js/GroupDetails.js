import React, { useState, useEffect } from 'react';
import axios from 'axios';

const GroupDetails = ({ group, isAdmin }) => {
  const [users, setUsers] = useState(group.users);

  const handlePermissionChange = (username, newPermission) => {
    // Implement logic to update user permissions in the group.
    // Make an HTTP request to your Java backend to update the group.
    axios.post("http://cs431-01.cs.rutgers.edu:8080/editprofile/user/save", { username, newPermission })
      .then((response) => {
        // Handle success, e.g., update the local state.
        const updatedUsers = users.map((user) => {
          if (user.username === username) {
            return { ...user, permission: newPermission };
          }
          return user;
        });
        setUsers(updatedUsers);
      })
      .catch((error) => {
        // Handle errors
        console.error('Error updating user permission:', error);
      });
  };

    return (
        <div className='groupDetails'>
            <h1>RUTidy</h1>
            <div className='groupDetailsForm'>
                <h2>Group Details: {group.name}</h2>
                <h3>Members:</h3>
                <ul>
                    {users.map((user) => (
                    <li key={user.username}>
                        {user.name}
                        {isAdmin && (
                        <select className='permissions' value={user.permission}
                        onChange={(e) => handlePermissionChange(user.username, e.target.value)}>
                            <option value = {2}>Member</option>
                            <option value = {1}>Manage</option>
                            <option value = {0}>Admin</option>
                        </select>)}
                    </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};