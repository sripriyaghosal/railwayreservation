import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import service from "./service";
const Profile=()=>{
    const username = localStorage.getItem('Uname');
    const jwt_token=localStorage.getItem("JWT");
    const [user_data, setUserData] = useState({});
    const redirect = useNavigate();
    const isloggedin = ()=>{
        if(!username)
        {
            redirect('/login');
        }
        else
        {
            service.getUser(username)
            .then((resp)=>{setUserData(resp.data)})
            .catch((error)=>{console.log(error)})
        }
    }
    const logout=()=>{
        localStorage.clear();
        redirect('/')
    }
    useEffect(()=>{
        isloggedin();
    },[username]);
    return(
        <><h2>Welcome {user_data.email?(<span>{user_data.name},</span>):null}</h2>
        <div>
            <span style={{float: 'right'}}>
                <button className="btn btn-info" onClick={()=>redirect('/update')}>Update Profile</button>&emsp;&emsp;
                <button className="btn btn-danger" onClick={logout}>Logout</button>
            </span>
        </div><br/>
        <div>{user_data.user_id?(
            <table>
                <tbody>
                    <tr>
                        <td>Your Name:</td>
                        <td>{user_data.name}</td>
                    </tr>
                    <tr>
                        <td>Your Unique ID:</td>
                        <td>{user_data.user_id}</td>
                    </tr>
                    <tr>
                        <td>Your Email ID:</td>
                        <td>{user_data.email}</td>
                    </tr>
                    <tr>
                        <td>Your Phone Number:</td>
                        <td>{user_data.phone}</td>
                    </tr>
                </tbody>
            </table>):null
        }
        </div>
        </>
    )
}
export default Profile;