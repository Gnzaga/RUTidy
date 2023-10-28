import React, {useState, useEffect} from "react";
import {useNavigate} from "react-router";
import CheckImage from "../image/check.png";
import ErrorImage from "../image/x.png";
import SearchIcon from "../image/search.png";
import axios from "axios";
import "../css/Home.css";

export default function Home (){
    const navigate = useNavigate();
    const [adminGroups, setAdminGroups] = useState([{"id": 1, "name": "group 1"}, {"id": 2, "name": "group 2"}]);
    const [userGroups, setUserGroups] = useState([{"id": 1, "name": "group 1"}, {"id": 2, "name": "group 2"}]);

    const [popUpSuccess, setPopUpSuccess] = useState(true);
    const [popUpMessage, setPopUpMessage] = useState(""); 

    const [searchGroupName, setSearchGroupName] = useState("");
    const [searchResults, setSearchResults] = useState([{"id": 1, "name": "group 1"}, {"id": 2, "name": "group 2"}]);
    
    const handleLogout = () => {
        sessionStorage.clear();
        navigate("/");
    }

    function queryGroups(){
        axios.get("http://localhost:8080/group/in", {params: {"userID": sessionStorage.getItem("userID")}})
        .then((response) => {
            const groups = response.data;
            const newAdminGroups = groups.filter((group) => groups.roles !== 2);
            const newUserGroups = groups.filter((group) => group.roles === 2);
            setAdminGroups([...newAdminGroups]);
            setUserGroups([...newUserGroups]);

        })
        .catch((error) => {
            setAdminGroups([]);
            setUserGroups([]);
        })
    }

    useEffect(() => {
        if (sessionStorage.getItem("userID") === null){
            navigate("/");
        }
        queryGroups();
    }, []);

    async function handleLeaveGroup(UIGroupID, isAdmin){
        const id = UIGroupID;

        await axios.delete("http://localhost:8080/group/leave", null, {params: {"UIGroupID": id}})
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

    async function handleJoinGroup(groupID){
        const userID = sessionStorage.getItem("userID");

        await axios.put("http://localhost:8080/group/join", null, {params: {groupID, userID}})
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

    async function handleSearch(){
        await axios.get("http://localhost:8080/group/name", {params: {"groupName": searchGroupName}})
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
                    <button onClick = {() => navigate("/edit/profile")}>Edit Profile</button>
                    <button onClick = {() => navigate("/create/group")}>Create Group</button>
                    <p onClick = {() => navigate("/groupdetails")}>Group Details</p>
                </div>
                <h2 onClick = {handleLogout} className = "homeLogout">logout</h2>
            </div>
            <div className = "homeBody">
            <div className = "homeGroupDiv">
                <h2 className = "homeGroupHeader">Admin Groups</h2>
                {adminGroups.map(group => {
                    return (
                        <div className = "homePageGroupDiv">
                            <h3 className = "homeGroupName">{group.name}</h3>
                            <p onClick = {() => handleLeaveGroup(group.uigroupID, true)}>Leave group</p>
                            <p onClick = {() => navigate("/groupdetails", {groupID: group.id})}>Group Details</p>
                        </div>
                    )
                }) 
                }
            </div>
            <div className = "homePageJoinGroup">
                <h2>Join Groups</h2>
                <div className = "homePageSearchDiv">
                    <input onChange = {(e) => setSearchGroupName(e.target.value)} value = {searchGroupName} placeholder = "search"></input>
                    <img onClick = {handleSearch}src = {SearchIcon} alt = "search"></img>
                    {searchResults.length !== 0 && <div className = "homePageSearchResults">
                        {searchResults.map(result => {
                            return (
                                <div className = "homePageSearchResult">
                                    <h3>{result.name}</h3>
                                    <p onClick = {() => handleJoinGroup(result.groupID)}>Join Group</p>
                                </div>
                            )
                        })}
                    </div>}
                </div>
            </div>
            <div className = "homeGroupDiv">
                <h2 className = "homeGroupHeader">User Groups</h2>
                {userGroups.map(group => {
                    return (
                        <div className = "homePageGroupDiv">
                            <h3 className = "homeGroupName">{group.name}</h3>
                            <p onClick = {() => handleLeaveGroup(group.uigroupID, false)}>Leave Group</p>
                        </div>
                    )
                })}
            </div>
            </div>
        </div>
    )
}