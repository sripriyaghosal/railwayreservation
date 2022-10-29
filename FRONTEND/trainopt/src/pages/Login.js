import React from "react";
import Navbar from "./Navbar";
import service from "./service";
import axios from "axios";
import { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
const Login=()=>{
 const [username,setUsername]=useState("");
 var token="";
 const navigate=useNavigate();
 const [password,setPassword]=useState("");
 const Tok=()=>{
    axios.post("http://localhost:8087/secure/authenticate",{username,password}).then((response)=>{
   token= (response.data.jwt_token)
   console.log("token",token)
   localStorage.setItem("JWT",token);
      localStorage.setItem("Uname",username);
   navigate('/')
    }).catch((error)=>{
      window.alert("invalid credentials");
    })
    
 }
 return (
    <>
    <h1>
    Login Page
    </h1>
    <label>Enter Username</label>
    <input type="text" required onChange={e=>setUsername(e.target.value)}></input>
    <div>
    <label>Enter Password</label>
    <input type="password" required onChange={e=>setPassword(e.target.value)}></input>
    </div>
    <button onClick={Tok}>
      Login
    </button>
    </>
 )
}
export default Login;
