import React, { useEffect, useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'bootstrap-icons/font/bootstrap-icons.css';
import '../styles/homeStyle.css';
import IRCTC from '../images/IRTC.jpeg';
import azadi from "../images/sam420.jpeg";
import Emblem from "../images/Emblem.jpeg";
import Navbar from "./Navbar";
import Footer from "./Footer";
import { useLocation,useNavigate } from "react-router-dom";
const Homepage = () => {
    const locate = useLocation();
    const spineTrain = useNavigate();
    const [from_station, setFrom_station] = useState('');
    const [formatted,setFormatted]= useState('');
    const [to_station, setTo_station] = useState('');
    const [errmsg,setErrmsg] = useState('');
    const [date, setDate] = useState(new Date(2021,11,25));
    const locations = ['New Jalpaiguri','Sealdah'];
    var isgood = true;
    const ErrorMsg = () =>{
        if(from_station=='' || to_station=='' || (date.getFullYear()==2021))
        {
            console.log("Input fields cannot be empty");
            setErrmsg("Input fields cannot be empty");
            isgood = false;
        }
        else if(from_station==to_station)
        {
            console.log("Source and destination cannot be the same");
            setErrmsg("Source and destination cannot be the same");
            isgood = false;
        }
        else if(!locations.includes(from_station) || !locations.includes(to_station))
        {
            console.log("Either Source or Destination or both not in our list");
            setErrmsg("Either Source or Destination or both not in our list");
            isgood = false;
        }
        // else if((Math.ceil(Math.abs(date-(new Date())))/(1000 * 60 * 60 * 24)>10) || (new Date())-date>0)
        // {
        //     console.log("Booking available for upcoming 10 days only");
        //     setErrmsg("Booking available for upcoming 10 days only");
        //     isgood = false;
        // }
        else
        {
            console.log("All good to go now");
            setErrmsg(null);
        }
    }
    const submitter = () => {
        console.log("From Station: ", from_station);
        console.log("To Station: ", to_station);
        console.log("Date: ", date);
        console.log("Formatted Date: ", formatted);
        ErrorMsg();
        spineTrain("/search",{state:{From: from_station, Date: formatted,To: to_station}});
    }
    const resetter = () =>{
        setDate(new Date(2021,11,25));
        setFrom_station('');
        setTo_station('');
        setErrmsg(null);
    }
    useEffect(() => {
        let month = "0";
        month = month + (date.getMonth() + 1);
        setFormatted( date.getFullYear() + "-" + month.slice(-2) + "-" + date.getDate());
        console.log("F Date: ", formatted);
        console.log(locate)
    }, [date,errmsg])
    return (
        <>
            <Navbar/>
            <div className="backg">
                <div className='irctc'>
                    <h2>Powered By <span><img src={IRCTC} height="90" /></span></h2>
                </div>
                <div className="searchbox">
                    <span id='sh'>Search Here</span>
                    <span style={{ float: 'right' }}><img src={azadi} alt="75" height="75" /></span>
                    <div>
                        <form>
                            <br/>
                            <div>Enter Source Station</div>
                            <datalist id='places'>
                                {locations.map((place) => (
                                    <option value={place} />
                                ))}
                            </datalist>
                            <input type='text' list='places' required placeholder="From Station" onChange={e => setFrom_station(e.target.value)}/>
                            <span style={{ float: "right", marginTop: "42px", marginRight:"22px" }}>
                                <img src={Emblem} height='160' alt="Emblem" />
                            </span>
                            <div style={{ paddingLeft: "80px", color: "#13590a" }}><i className="bi bi-arrow-down-up"></i></div>
                            <div>Enter Destination Station</div>
                            <input type='text' list='places' required placeholder="To Station" onChange={e => setTo_station(e.target.value)}/>
                            
                            <br /><p/><p/>
                            <div>Select Date</div>
                           <input type='date' required placeholder="yyyy-mm-dd" min="2022-09-01" max="2022-12-31" onChange={e => setDate(e.target.valueAsDate)} />
                            <br /><br /><p />
                            <input type='button' value='Search' className="btn btn-success" onClick={submitter} />
                            &emsp;&emsp;&emsp;
                            <input type="reset" value='Reset' className='btn btn-info' onClick={resetter}/>
                        </form>
                        <div className="errormsg">{errmsg}</div>
                    </div>
                </div>
            </div>
            <Footer/>
        </>
    );
}
export default Homepage;