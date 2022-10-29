import React, { useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import './style3.css';
import Footer from "./Footer";
import Navbar from "./Navbar";
const Ticket=()=>{
 //const location = useLocation();
 var JSONDATA = localStorage.getItem("ticket");
 const ticket = JSON.parse(JSONDATA);
 const moveto = useNavigate();
//  const ticket = location.state;

 useEffect(()=>{
    if(!ticket.pnr)
    {
        moveto('/');
    }
    console.log(ticket);
 },[])
 return(
    <>
    {ticket.pnr? (<div style={{backgroundColor: "#fabe3c"}}><Navbar/>
        <div className="container-fluid">
            <div className="first">
                <div>PNR Number: {ticket.pnr} <span style={{float: "right"}}>Ticket Number: {ticket.ticket_no}</span></div>
                <div>Journey from {ticket.train.from_station} to {ticket.train.to_station}</div>
                <div>{ticket.train.train_id}, {ticket.train.train_name}</div>
                <div>Journey Date: {ticket.date}</div>
                <div>Passenger Details:</div>
                <div>
                    <table>
                    <thead>
                       <tr>
                            <td>Serial No.</td>
                            <td>Passenger Name</td>
                            <td>Passenger Age</td>
                            <td>Gender</td>
                            <td>Seat Number</td>
                            <td>Train Class</td>
                        </tr>
                    </thead>
                    <p/>
                    <tbody>
                        {ticket.passengers.map((pass, index)=>(
                            <tr>
                                <td>{index+1}</td>
                                <td>{pass.pass_name}</td>
                                <td>{pass.pass_age}</td>
                                <td>{pass.pass_gender}</td>
                                <td>{pass.pass_seat_no}</td>
                                <td>{pass.pass_train_class}</td>
                            </tr>
                        ))}
                    </tbody>
                    </table>
                    <p/>
                    <div>Total Amount Paid: Rs.{Math.ceil(ticket.fare)}</div>
                    <br/>
                    <div>Ticket Booked at: {ticket.time}</div>
                    <br/>
                    <h5>Happy Journey. Passenger Helpline Number: 139</h5>
                </div>
            </div><br/><br/><center>
            <Link className="btn btn-secondary" to='/'>Continue Searching</Link>
            </center><br/>
        </div><Footer/>
    </div>)
    :null}
    </>
 )
}
export default Ticket;
