import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import "../css/Chores.css";
import axios from "axios";


export default function UserChores(props){
    const [chores, setChores] = useState([
        {name: "chore1", key: '1'},
        {name: "chore2", key: '2'},
        {name: "chore3", key: '3'},
        {name: "chore4", key: '4'},
        {name: "chore5", key: '5'},
        {name: "chore6", key: '6'},
        {name: "chore7", key: '7'},
    ]);
    const listItems = chores.map((chores) =>
        <div>
            <input type = "checkbox"></input>
        </div>
        <div key={chores.key}>
            <h2 className="choreItem">{chores.name}</h2>
        </div>
    );
    const navigate = useNavigate();
    
    async function mapChores(){
        listItems = chores.map((chores) =>
            <div className="choreItem" key={chores.key}>
                {chores.name}
            </div>
        )
    };

    /*async function handleEdit(){
        if (username === "" || name === "" || email === "") { 
            setError("Please enter all fields!");
            return;
        }
        if (password !== cpassword) {
            setError("Passwords do not match!")
            return;
        }

        axios.post("http://cs431-01.cs.rutgers.edu:8080/user/update?userID=" + sessionStorage.getItem("userID").toString(), {username, password, name, email})
        .then((response) => { 
            const {message} = response.data;
            if (message !== "Account Updated!"){
                setError(message);
                return;
            }
            sessionStorage.setItem("name", name);
            sessionStorage.setItem("email", email);
            sessionStorage.setItem("username", username);

            navigate("/profile");
        })
        .catch((error) => { 
            setError("An unexpected error has occurred!");
            return;
        })
        
    }*/

    return (
        <div className = "choresPage">
            <div className = "leftPane">
                <div className = "listView">
                    <br></br>
                    <h1>List of Chores</h1>
                    {listItems}
                </div>
            </div>
            <div className = "rightPane">
                <div className = "detailView">
                    <br></br>
                    <h1>Chore Details</h1>
                </div>
            </div>
        </div>
    );
}