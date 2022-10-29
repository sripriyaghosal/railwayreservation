import React from "react";
import "../styles/navStyle.css";
import Logo from "../images/Railways.jpeg";
import Train from "../images/Trainweb.jpeg";
import { Link } from "react-router-dom";
const Navbar = () => {
    const Username=localStorage.getItem("Uname");
    return (
        <div>
            <div className="nav1">
               <Link to='/'> <span className="navicon"><img src={Logo} height="95" alt="Train Logo" /></span></Link>
                <span className="navtext"><b>
                <Link to="/Login" style={{ textDecoration: "none" }}>
                    <span className="txts">Login</span>&emsp;&nbsp;
                    </Link>
                    {
                        Username?(
                            <Link to="/Profile" style={{ textDecoration: "none" }}>
                    <span className="txts">Profile</span>
                    </Link>
                        ):(<Link to="/Signup" style={{ textDecoration: "none" }}>
                        <span className="txts">Signup</span></Link>)
                    }
                    &emsp;&nbsp;
                    <Link to="/about" style={{ textDecoration: "none" }}>
                        <span className="txts">About</span>
                    </Link>&emsp;&nbsp;
                    <Link to="/contact" style={{ textDecoration: "none" }}>
                    <span className="txts">Contact Us</span>
                    </Link>
                </b></span>
                <span className="name">
                    Rail Yatra&nbsp;&nbsp;<span className='dess'>철도 रेल्वे Đường sắt รถไฟ
</span>
                    &emsp;&emsp;&nbsp;
                    <span><img src={Train} height="50" alt="Train"/></span>
                </span>
            </div>
        </div>
    );
}
export default Navbar;