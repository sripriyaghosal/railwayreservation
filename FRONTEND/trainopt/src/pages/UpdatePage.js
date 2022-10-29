import React, { useEffect, useState } from "react";
import Navbar from './Navbar';
import { useNavigate } from "react-router-dom";
import service from "./service";
import Footer from "./Footer";
const UpdatePage=()=>{
   const user=localStorage.getItem('Uname');
   const navigate = useNavigate();
   const [user_id, setUser_id]=useState('');
   const [email, setEmail] = useState('');
   const [name, setName] = useState('');
   const [phone, setPhone] = useState('');
   const [password, setPassword] = useState('');
   const user_data={user_id, email, name, phone, password}
   const [message,setMessage] = useState('');
   const [nameerr,SetNameerr] = useState('');
    const [emailerr,setEmailerr] = useState('');
    const [paserr,setPaserr] = useState('');
    const [phoneerr,setPhoneerr] = useState('');
    var is_all_good=false, name_ok=false, email_ok=false,pass_ok=false, ph_ok=false;
   const isloggedin=()=>{
    if(!user)
    {navigate('/login')}
    else
    {
        service.getUser(user)
        .then((resp)=>{
            setName(resp.data.name);
            setEmail(resp.data.email);
            setPhone(resp.data.phone);
            setPassword(resp.data.password);
            setUser_id(resp.data.user_id)
        }).catch((err)=>console.log("Errors",err))
    }
   }
   const validate=()=>{
    if (user_data.name.length === 0) { SetNameerr("Name is required"); name_ok=false;}
        else{SetNameerr(''); name_ok=true;}

        if (user_data.email.length === 0) { setEmailerr("Email is required"); email_ok=false;}
        else if (!/\S+@\S+\.\S+/.test(user_data.email)) { setEmailerr("Invalid Email id"); email_ok=false;}
        else{setEmailerr(''); email_ok=true;}

        if (user_data.password.length === 0) 
        { setPaserr("Password is Required"); pass_ok=false;}
        else if (user_data.password.length < 5 || user_data.password.length > 12) 
        { setPaserr("Password should be between 5 to 12 characters"); pass_ok=false;}
        else{console.log(user_data.password.length); setPaserr(''); pass_ok=true;}

        if (user_data.phone==='') { setPhoneerr("Phone Number is required"); ph_ok=false;}
        else if(phone < 1000000000 || phone > 9999999999){setPhoneerr("Phone Number should be of 10 digits"); ph_ok=false;}
        else{setPhoneerr(''); ph_ok=true;}

        if(is_all_good===false && name_ok===true && email_ok===true && pass_ok===true && ph_ok===true)
        {is_all_good=true;}
   }
   const handleUpdate=()=>{
    validate();
    if(is_all_good)
    {
        service.getduplicateusers(email)
        .then((response)=>{
            if(response.data.length==0 || email==user)
            { console.log(response.data.length);
                console.log("Old",user);
                console.log("New",email);
                console.log(response.data);
                service.updateUserByEmail(user,user_data)
                .then((response)=>{
                    setMessage("Updated the details, visit profile to see changes");
                    console.log("Updated user",response.data);
                    localStorage.setItem("Uname",email);
                }).catch((error)=>{console.log("Error Updating User",error)})
            }
            else
            {setMessage('Email Id exists for other users');}
        }).catch((error)=>{console.log("Error fetching Users",error)})
    }
   }
   const gotopro=()=>{navigate('/profile')}
   useEffect(()=>{
    isloggedin();
   },[])
   return(
    <>
    <Navbar/>
    <center><h3>Update Your Profile</h3></center>
    {user?(
        <div>
    <table>
        <tbody>
            <tr>
                <td>Name:</td>
                <td><input type='text' required value={name} onChange={e=>setName(e.target.value)}/>&emsp;&emsp;{nameerr}</td>
            </tr>
            <tr>
                <td>Email ID:</td>
                <td><input type='text' required value={email} onChange={e=>setEmail(e.target.value)}/>&emsp;&emsp;{emailerr}</td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type='number' required value={phone} onChange={e=>setPhone(e.target.valueAsNumber)}/>{phoneerr}</td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='text' required value={password} onChange={e=>setPassword(e.target.value)}/>{paserr}</td>
            </tr>
            <br/>
            <tr>
                <td><button className="btn btn-success" onClick={handleUpdate}>Update Details</button></td>
                <td><button className="btn btn-dark" onClick={gotopro}>Back to Profile</button></td>
            </tr>
        </tbody>
    </table>
    <br/>{message}
    </div>
    ):null}
    <Footer/>
    </>
   )
}
export default UpdatePage;