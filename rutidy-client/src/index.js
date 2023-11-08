import React from 'react';
import ReactDOM from 'react-dom/client';
import Login from "./js/Login";
import Signup from "./js/Signup";
import Home from "./js/Home";
import GroupDetails from "./js/GroupDetails";
import Profile from "./js/Profile";
import EditProfile from "./js/EditProfile";
import UserChores from './js/UserChores';
import CreateGroup from "./js/CreateGroup";
import AdminTasks from "./js/AdminTasks";
import {BrowserRouter, Routes, Route} from "react-router-dom";


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path = "/" element = {<Login />}></Route>
      <Route path = "/signup" element = {<Signup />}></Route>
      <Route path = "/home" element = {<Home/>}></Route>
      <Route path = "/profile" element = {<Profile />}></Route>
      <Route path = "/profile/edit" element = {<EditProfile />}></Route>
      <Route path = "/chores" element = {<UserChores />}></Route>
      <Route path = "/groupdetails/:groupID" element = {<GroupDetails/>}></Route>
      <Route path = "/create/group" element = {<CreateGroup/>}></Route>
      <Route path = "/admin/tasks" element = {<AdminTasks />}></Route>
    </Routes>
  </BrowserRouter>
);

