import React from 'react';
import ReactDOM from 'react-dom/client';
import Login from "./js/Login";
import Signup from "./js/Signup";
import GroupDetails from "./js/GroupDetails";
import {BrowserRouter, Routes, Route} from "react-router-dom";


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path = "/" element = {<Login />}></Route>
      <Route path = "/signup" element = {<Signup />}></Route>
      <Route path = "/groupdetails" element = {<GroupDetails />}></Route>
    </Routes>
  </BrowserRouter>
);

