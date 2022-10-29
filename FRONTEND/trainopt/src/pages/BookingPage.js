import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import service from "./service";
const BookingPage=()=>{
    const navigate = useNavigate();
    const [count, setCount]=useState(1);
    const {train_id, train_class, date, seats} = useParams();
    const [train,setTrain] = useState({});

    const [pass_id, setPass_id]=useState(1); 
    const [pass_name, setPass_name] = useState('');
    const [pass_age, setPass_age] = useState(0);
    const [pass_gender, setPass_gender] = useState('');
    const [pass_address, setPass_address] = useState('');
    const [pass_credit_no, setPass_credit_no] = useState(0);
    const [pass_bank_name, setPass_bank_name] = useState('');
    const pass_train_class = train_class;
    const [pass_mobile, setPass_mobile] = useState(0);
    const [pass_seat_no, setPass_seat_no] = useState(seats);
    const passenger = {pass_id, pass_name, pass_age, pass_address, pass_gender, pass_credit_no, pass_bank_name, pass_train_class, pass_mobile, pass_seat_no};

    const [pnr, setPnr] = useState('');
    const [ticket_no, setTicket_no] = useState(0);
    const [time,setTime] = useState('');
    let fare = 1000;
    const user_email = localStorage.getItem('Uname');
    const [passengers, setPassengers] = useState([]);
    let temp_passengers=[];
    const bookedTicket = {pnr, ticket_no, date, time, fare, train, train_class, user_email, passengers};
    const [allok, setAllOk]=useState(false);

    //console.log(train_id, train_class, date);
    const isloggedin=()=>{
        if(!localStorage.getItem('JWT'))
        {
            navigate('/login');
        }
        else{
            service.gettheTrain(train_id)
            .then((response)=>{
                setTrain(response.data);

            }).catch((error)=>{console.log("Error getting Train",error)});
        }
    }
    const savePassenger=(ev)=>{
        ev.preventDefault();
        setPass_seat_no(pass_seat_no-1);
        setCount(count+1);
        setPass_id(pass_id+1);
        temp_passengers.push(passenger);
        service.savePassenger(passenger)
        .then(()=>{
            service.getAllPass()
            .then((resp)=>{
                setPassengers(resp.data);
            })
            .catch((err)=>{
                console.log("Error",err);
            });
            setPass_name(''); setPass_age(''); setPass_gender(''); setPass_address(''); setPass_mobile(0);
        }
        ).catch((error)=>console.log("Saving Error",error));
        console.log("Temporary",temp_passengers);
        console.log("Tseats",pass_seat_no);
    }

    const handleDelete=(id)=>{
        service.removeOnepassenger(id)
        .then(()=>{
            service.getAllPass().then((resp)=>{
                setPassengers(resp.data);
                setPass_seat_no(pass_seat_no+1);
                setCount(count-1);
            }).catch(()=>console.error());
        }).catch((eror)=>console.log("Error deleting passenger",eror));
        console.log("Seats after deletion",pass_seat_no);
    }
    const resetPass=()=>{
        service.removeAll().then((resp)=>{
            console.log(resp.data);
            setPassengers([]);
            setCount(1);
            setPass_bank_name('');
            setPass_credit_no('');
            setPass_id(1);
        }).catch(()=>console.error());
    }
    const saveDetails=()=>
    {
        // localStorage.setItem('PNR',"10000-A00");
        // localStorage.setItem("TicketNo","1000000");
        if(passengers.length > 0)
        {
        if(train_class === "AC1")
        {
            fare=(fare*1.6*(passengers.length));
        }
        else if(train_class === "AC2")
        {
            fare=(fare*1.4*(passengers.length));
        }
        else if(train_class === "AC3")
        {
            fare=(fare*1.2*(passengers.length));
        }
        else
        {
            fare=(fare*1*(passengers.length));
        }
        setPnr("45678-B01");
        setTicket_no(1000000);
        setTime(new Date().toLocaleTimeString("en-US"));
        setAllOk(true);
        }
        else{
            window.alert("Add one Passenger atleast");
        }
        console.log("All good ?",allok);
    }
    const bookTheTicket=()=>{
        console.log(passengers);
        console.log(bookedTicket);
        resetPass();
        setAllOk(false);
        localStorage.setItem("ticket",JSON.stringify(bookedTicket))
    // navigate('/final-ticket', {state:bookedTicket});
   window.location.href="http://localhost:9090?fare=" +fare;
}
    useEffect(()=>{
        isloggedin();
        console.log(passengers);
        console.log("Available Seats",pass_seat_no);
    },[pass_seat_no]);
    return(
        <>
        <h3>Fill Up the Passenger Details</h3>
        {train.train_name?(
        <div>
            <span>Train No: {train_id}</span>&emsp;&emsp;
            <span>Train Name: {train.train_name}</span>&emsp;&emsp;
            <span>Train Class: {train_class}</span>&emsp;&emsp;
            <span>Journey Date: {date}</span>&emsp;&emsp;
            <span>Base Fare: {fare}</span>
        </div>
        ):null}
        <div>
            For Refund related purpose, in case of ticket cancellation, please <br/>Enter credit your Card Number:
            &emsp;
            <input type="number" required value={pass_credit_no} onChange={e=>setPass_credit_no(e.target.valueAsNumber)}></input>
            <br/>
            <label>Bank Name:</label>
            <input type="text" required value={pass_bank_name} onChange={e=>setPass_bank_name(e.target.value)}></input>
        </div>
        {passengers.length ? (
            <div>
                {passengers.map((pass, index)=>(
                    <div>
                    <div>Passenger {index+1}</div>
                    <div>{pass.pass_name}</div>
                    <div>{pass.pass_age}</div>
                    <div>{pass.pass_gender}</div>
                    <div>{pass.pass_address}</div>
                    <div>{pass.pass_phone}</div>
                    <br/>
                    <button className="btn btn-danger" onClick={()=>{handleDelete(pass.pass_id)}}>Delete</button>
                    <hr/>
                    </div>
                ))}
            </div>
        ):null}
        {count<=6?(
        <div>
            <table>
                <thead>Passenger {count}:</thead>
            <tbody>
                <tr>
                   <td><label>Enter Passenger Name:</label></td>&emsp;
                   <td><input type="text" required value={pass_name} placeholder="Enter Passenger name" onChange={e=>setPass_name(e.target.value)}/></td>
                </tr>
                <tr>
                   <td><label>Enter Passenger Age:</label></td>&emsp;
                   <td><input type="number" required value={pass_age} placeholder="Enter Passenger Age" onChange={e=>setPass_age(e.target.value)}/></td>
                </tr>
                <tr>
                    <datalist id='genders'>
                        <option value="Male"/>
                        <option value="Female"/>
                        <option value="Others"/>
                    </datalist>
                   <td><label>Enter Passenger Gender:</label></td>&emsp;
                   <td><input type="text" list="genders" required value={pass_gender} onChange={e=>setPass_gender(e.target.value)} placeholder="Enter Passenger Gender"/></td>
                </tr>
                <tr>
                   <td><label>Enter Passenger Address:</label></td>&emsp;
                   <td><textarea rows="4" cols="50" value={pass_address} onChange={e=>setPass_address(e.target.value)}/></td>
                </tr>
                <tr>
                   <td><label>Enter Passenger Phone:</label></td>&emsp;
                   <td><input type="number" required value={pass_mobile} onChange={e=>setPass_mobile(e.target.valueAsNumber)}/></td>
                </tr>
            </tbody>
            </table>
            <button className="btn btn-success" onClick={(e)=>{savePassenger(e)}}>Save Passenger</button>
        </div>
        ):null}
        <br/>
        {allok?(<button className="btn btn-primary" onClick={bookTheTicket}>Book Ticket</button>):
        (<button className="btn btn-warning" onClick={saveDetails}>Save All Details</button>)}
        {/* <button onClick={()=>window.location.href="http://localhost:9090"}>Payfare</button> */}
        </>
    )
}
export default BookingPage;