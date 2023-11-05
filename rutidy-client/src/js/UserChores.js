import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Chores.css";
import Check from "../image/check.png"
import X from "../image/x.png"
import axios from "axios";


export default function UserChores(props){
    const [chores, setChores] = useState([
        {status: "Ongoing", name: "chore1xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", key: 1},
        {status: "Complete", name: "chore2", key: 2},
        {status: "Complete", name: "chore3", key: 3},
        {status: "Ongoing", name: "chore4", key: 4},
        {status: "Ongoing", name: "chore5", key: 5},
        {status: "Complete", name: "chore6", key: 6},
        {status: "Ongoing", name: "chore7", key: 7},
        {status: "Complete", name: "chore8", key: 8},
        {status: "Complete", name: "chore9", key: 9},
        {status: "Ongoing", name: "chore10", key: 10},
        {status: "Complete", name: "chore11", key: 11},
        {status: "Complete", name: "chore12", key: 12},
        {status: "Ongoing", name: "chore13", key: 13},
        {status: "Complete", name: "chore14", key: 14},
        {status: "Complete", name: "chore15", key: 15},
        {status: "Complete", name: "chore16", key: 16},
        {status: "Ongoing", name: "chore17", key: 17},
        {status: "Complete", name: "chore18", key: 18}
    ]);
    const [displayChore, setDisplayChore] = useState(chores.find(chore => chore.key === 1));

    const listItems = chores.map((chores) =>
        <tr>
            <td><img className="statusImage" src = {chores.status === "Complete"? Check: X}></img></td>
            <td><h2 className="choreItem" onClick={()=>displayDetails(chores.key)}>{chores.name}</h2></td>
        </tr>
    )

    const navigate = useNavigate();

    async function  displayDetails(chorekey){
        //Task displayChore = 
        setDisplayChore(chores.find(chore => chore.key === chorekey));
    };

    return (
        <div className = "choresPage">
            <div className = "leftPane">
                <div className = "listView">
                    <br></br>
                    <h1>List of Chores</h1>
                    <table className="choreTable">{listItems}</table>
                </div>
            </div>
            <button className="returnButton" onClick={()=>navigate("/home")}>Return</button>
            <div className = "rightPane">
                <div className = "detailView">
                    <br></br>
                    <h1>Chore Details</h1>
                    <table>
                        <tr>
                            <td><h2 className="choreInfoType">Name: </h2></td>
                            <td><h2 className="choreInfo">{displayChore.name}</h2></td>
                        </tr>
                        <tr>
                            <td>
                                <h2 className="choreInfoType">Status: </h2>
                                <button className="completionButton">Mark Task As Complete!</button>
                            </td>
                            <td><h2 className="choreInfo">{displayChore.status}</h2></td>
                        </tr>
                        <tr>
                            <td><h2 className="choreInfoType">Due Date: </h2></td>
                            <td><h2 className="choreInfo">{displayChore.status}</h2></td>
                        </tr>
                        <tr>
                            <td><h2 className="choreInfoType">Description: </h2></td>
                        </tr>
                    </table>
                    <div>
                        <h2 className="choreDescription">{displayChore.status}</h2>
                    </div>
                </div>
            </div>
        </div>
    );
}