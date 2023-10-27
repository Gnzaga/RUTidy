import React from 'react';
import ReactDOM from 'react-dom/client';
import Login from "./js/Login";
import Signup from "./js/Signup";
import Home from "./js/Home";
import GroupDetails from "./js/GroupDetails";
import Profile from "./js/Profile";
import CreateGroup from "./js/CreateGroup";
import {BrowserRouter, Routes, Route} from "react-router-dom";


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path = "/" element = {<Login />}></Route>
      <Route path = "/signup" element = {<Signup />}></Route>
      <Route path = "/home" element = {<Home/>}></Route>
      <Route path = "/groupdetails" element = {<GroupDetails/>}></Route>
      <Route path = "/edit/profile" element = {<Profile/>}></Route>
      <Route path = "/create/group" element = {<CreateGroup/>}></Route>
    </Routes>
  </BrowserRouter>
);

