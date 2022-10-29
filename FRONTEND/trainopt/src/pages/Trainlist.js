import React, { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import service from "./service";
import Footer from "./Footer";
const Trainlist =() => {
    const locate=useLocation();
    const navi = useNavigate();
    const [from,setFrom] = useState('');
    const [to,setTo] = useState('');
    const [date,setDate] = useState('');
    const [csdate,setCsdate] = useState('')
    const [trains, setTrains] = useState([]);
    const getTrains=()=>{
        if(locate.state.From)
        {
            setFrom(locate.state.From);
            setTo(locate.state.To);
            setDate(locate.state.Date);
        }
        service.fetchTrains(from,to,date).then((response)=>{
            setTrains(response.data.trains);
            console.log(response.data);
        }).catch((error)=>console.log(error));
        
    }
    const book=(train_no, train_class, date, seats)=>{
        navi(`/book-ticket/fillForm/${train_no}/${train_class}/${date}/${seats}`);
    }
    const logout=()=>{
        localStorage.removeItem('JWT');
        localStorage.removeItem('Uname');
        navi('/')
    }
    useEffect(()=>{
        getTrains();
        if(date!=="")
        {
            setCsdate(date.slice(8)+'/'+date.substring(5,7)+'/'+date.substring(0,4))
        }
        console.log(from,to,date)
    },[from])
    return(
        <>
        <div className="navbar1">Here Are Your Train Details</div>
        <div style={{backgroundColor: 'aquamarine'}}>
        <div className="container" style={{minHeight: '573px', width:'100%'}}>
            {localStorage.getItem("JWT")?(
            <div><span style={{float: 'right', marginRight: "55px", marginTop:"8px"}}>
                <Link className="btn btn-primary" to='/profile'>Profile</Link>&emsp;&nbsp;
            <button className="btn btn-danger" onClick={logout}>Logout</button>
            </span>
            <br/><p/></div>):
            (<div><span style={{float: 'right', marginRight: "55px", marginTop:"8px"}}>
                <button className="btn btn-primary" onClick={()=>{navi('/login')}}>Login</button>
                </span><br/><p/></div>)}
        <div className="row">
        <div className="col-md-3 aside1"><div style={{fontSize: '24px', fontFamily:'Times New Roman', textAlign:'center'}}>Search Details</div>
        <br/>
        <div className="aside2">Source Station: {from}</div>
        <div className="aside2">Destination Station: {to}</div>
        <div className="aside2">Date: {csdate}</div>
        <br/>
        <div className="btn btn-primary" onClick={()=>{navi('/')}}>Modify Search</div>
        </div>
        {trains.length>0?(
        <div className="col-md-8">
        {trains.map((train)=>(
            <div className="traineach">
            <div className="trnno">{train.train_id}</div>
            <span id="tname">{train.train_name}</span>
            <hr/>
            <div className="row" style={{textAlign:'center'}}>
                {train.total_Seat_ac3>0?(
                    <div className="col-md-3">
                        AC 3 Tier<br/>
                        <div className="btn btn-success" onClick={()=>{book(train.train_id,"AC3",date,train.total_Seat_ac3);}}>
                            Available: {train.total_Seat_ac3}
                        </div>
                    </div>
                ):<div className="col-md-3">
                AC 3 Tier<br/>
                <div className="btn btn-danger">Not Available</div>
            </div>}
            {train.total_Seat_ac2>0?(
                    <div className="col-md-3">
                        AC 2 Tier<br/>
                        <div className="btn btn-success" onClick={()=>{book(train.train_id,"AC2",date,train.total_Seat_ac2);}}>
                            Available: {train.total_Seat_ac2}</div>
                    </div>
                ):<div className="col-md-3">
                AC 2 Tier<br/>
                <div className="btn btn-danger">Not Available</div>
            </div>}
            {train.total_Seat_ac1>0?(
                    <div className="col-md-3">
                        AC First Class<br/>
                        <div className="btn btn-success" onClick={()=>{book(train.train_id,"AC1",date,train.total_Seat_ac1);}}>
                            Available: {train.total_Seat_ac1}</div>
                    </div>
                ):<div className="col-md-3">
                AC First Class<br/>
                <div className="btn btn-danger">Not Available</div>
            </div>}
            {train.total_Seat_sleeper>0?(
                    <div className="col-md-3">
                        Sleeper Class<br/>
                        <div className="btn btn-success" onClick={()=>{book(train.train_id,"Sleeper",date,train.total_Seat_sleeper);}}>
                            Available: {train.total_Seat_sleeper}</div>
                    </div>
                ):<div className="col-md-3">
                Sleeper Class<br/>
                <div className="btn btn-danger">Not Available</div>
            </div>}
            </div>
            <br/>
            <div>Base Fare: {train.base_fare}</div>
            <br></br>
            </div>
        ))}
        </div>
        ):<div id="tname" className="notrain">
            Sorry! No Trains Available for this route</div>}
        </div>
        </div>
        </div>
        <Footer/>
        </>
    )
}
export default Trainlist;