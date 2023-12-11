import React, {useState, useEffect, useCallback} from "react";
import {useNavigate} from "react-router";
import CheckImage from "../image/check.png";
import ErrorImage from "../image/x.png";
import SearchIcon from "../image/search.png";
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';

import axios from "axios";
import "../css/Home.css";

/**
 * Component that represents the home page
 * @returns component representing the home page
 */
export default function Home (){
    const navigate = useNavigate();
    const [adminGroups, setAdminGroups] = useState([{"id": 1, "name": "group 1"}, {"id": 2, "name": "group 2"}]);
    const [userGroups, setUserGroups] = useState([{"id": 1, "name": "group 1"}, {"id": 2, "name": "group 2"}]);

    const [groups, setGroups] = useState([]);

    const [popUpSuccess, setPopUpSuccess] = useState(true);
    const [popUpMessage, setPopUpMessage] = useState(""); 

    const [loading, setLoading] = useState(false);

    const [searchGroupName, setSearchGroupName] = useState("");
    const [searchResults, setSearchResults] = useState([]);
    
    /**
     * Logs the user out of software, clears user information, and navigates to login page
     */
    const handleLogout = () => {
        sessionStorage.clear();
        navigate("/");
    }

    /**
     * Memoized function that queries the groups the user is part of
     */
    const queryGroups = useCallback(() => {
        axios
        .get("http://cs431-01.cs.rutgers.edu:8080/group/in", {
            params: { userID: sessionStorage.getItem("userID") }
          })
          .then((response) => {
            console.log(response.data);
            const groups = response.data;
      
            // Correct the filter conditions to access group.group.roles
            const newAdminGroups = groups.filter((group) => group.roles !== 2);
            const newUserGroups = groups.filter((group) => group.roles === 2);
      
            // Update the state once with the filtered results
            setAdminGroups([...newAdminGroups]);
            setUserGroups([...newUserGroups]);
      
            console.log(adminGroups); // This will not immediately reflect the updated state
            console.log(userGroups); // This will not immediately reflect the updated state
          })
          .catch((error) => {
            setAdminGroups([]);
            setUserGroups([]);
          });
      }, []);
      
    /**
     * Function that is called after the page is rendered to query groups the user is in
     */
    useEffect(() => {
        if (sessionStorage.getItem("userID") === null){
            navigate("/");
        }
        queryGroups();
        
    }, [navigate, queryGroups]);

    /**
     * Handles logic for a user leaving a group specified
     * @param {*} UIGroupID the group id 
     * @param {*} isAdmin boolean representing if the user is an admin of the group
     */
    async function handleLeaveGroup(UIGroupID, isAdmin){
    

        await axios.delete("http://cs431-01.cs.rutgers.edu:8080/group/leave", {params: 
            {
                userID: sessionStorage.getItem("userID"), 
                groupID: UIGroupID
            }
        }
        )
        .then((response) => {
            if (isAdmin){
                const newAdminGroups = adminGroups.filter(group => group.uigroupID !== UIGroupID);
                setAdminGroups([...newAdminGroups]);
            } else {
                const newUserGroups = userGroups.filter(group => group.uigroupID !== UIGroupID);
                setUserGroups([...newUserGroups]);
            }

            setPopUpMessage(true);
            setPopUpMessage("Successfully left group!");
        })
        .catch((error) => {
            setPopUpSuccess(false);
            setPopUpMessage("Could not leave group!");
        })

        setTimeout(() => setPopUpMessage(""), 2000);
    }

    /**
     * Handles logic for user joining a group
     * @param {*} groupID group id of the group the user joins
     */
    async function handleJoinGroup(groupID){
        const userID = sessionStorage.getItem("userID");

        await axios.put("http://cs431-01.cs.rutgers.edu:8080/group/join", null, {params: {groupID: groupID, userID: userID}})
        .then((response) => {
            const message = response.data;
            if (message !== "Success!"){
                setPopUpSuccess(false);
            } else { 
                queryGroups();
                setPopUpSuccess(true);
            }
            setPopUpMessage(message);
        })

        setTimeout(() => setPopUpMessage(""), 2000);
    }

    /**
     * Handles the logic that allows user to search for groups to join
     */
    async function handleSearch(){
        await axios.get("http://cs431-01.cs.rutgers.edu:8080/group/name", {params: {"groupName": searchGroupName}})
        .then((response) => {
            setSearchResults([...response.data]);
        })
        .catch((error) => {
            setPopUpSuccess(false);
            setPopUpMessage("Could not find group with that name!");
        });

        setTimeout(() => setPopUpMessage(""), 2000);
    }

   
    return (
        <div className = "homePage">
            {popUpMessage !== "" && <div className = "homePagePopUp">
                <img alt = "popup" src = {popUpSuccess? CheckImage: ErrorImage}></img>
                <p>{popUpMessage}</p>
            </div>}
            <div className = "homeNavigationBar">
                <h1>RUTidy</h1>
                <div className = "homeNavigationButtonDiv">
                    <Button className = "btn-primary" size = "sm" onClick = {() => navigate("/profile")}>{sessionStorage.getItem("username")}'s Profile</Button>
                    <Button className = "btn-primary" size = "sm" onClick = {() => navigate("/create/group")}>Create Group</Button>
                </div>
                
                <Button onClick = {handleLogout} className = "btn-secondary" size = "lg">logout</Button>
            </div>
            <div className = "homePageJoinGroup">
                <h2>Join Groups</h2>
                <div className = "homePageSearchDiv">
                    <div className = "searchSection">
                        <input onChange = {(e) => setSearchGroupName(e.target.value)} value = {searchGroupName} placeholder = "search"></input>
                        <img onClick = {handleSearch}src = {SearchIcon} alt = "search"></img>
                    </div>
                    {searchResults.length !== 0 && <div className = "homePageSearchResults">
                        {searchResults.map(result => {
                            return (
                                <div className = "homePageSearchResult">
                                    <h3>{result.name}</h3>
                                    <Button className = "btn-success" size = "sm" onClick = {() => handleJoinGroup(result.groupID)}>Join Group</Button>
                                </div>
                            )
                        })}
                    </div>}
                </div>
            </div>
            <div className = "groupsDiv">
            <div className = "homeGroupDiv">
                <h2 className = "homeGroupHeader">Admin Groups</h2>
                {adminGroups.map(group => {
                    return (
                        <div key={group.group?.groupID} className = "homePageGroupDiv">
                            <Link to={'/groupdetails/' + (group.group?.groupID || '')} className="homeGroupName">
                            {group.group?.name || ''}</Link>
                            <Link to={'/admin/tasks/' + (group.group?.groupID || '')}>Go to Admin Tasks</Link>
                           
                        </div>
                    )
                }) 
                }
            </div>
            <div className = "homeGroupDiv">
                <h2 className = "homeGroupHeader">User Groups</h2>
                {userGroups.map(group => {
                    return (
                        <div key={group.group?.groupID} className = "homePageGroupDiv">
                            <Link to={'/chores/' + (group.group?.groupID || '')} className="homeGroupName">
        {group.group?.name || ''}</Link>
                            <Button size = "sm" className = "btn-danger" onClick = {() => handleLeaveGroup(group?.group.groupID, false)}>Leave Group</Button>
                        </div>
                    )
                })}
            </div>
            </div>
            
            
        </div>
    )
            
}
