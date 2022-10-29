// import logo from './logo.svg';
// import './App.css';

import { BrowserRouter, Route, Routes } from "react-router-dom";
import Homepage from "./pages/Homepage";
import Footer from "./pages/Footer";
import Navbar from "./pages/Navbar";
import Trainlist from "./pages/Trainlist";
import About from "./pages/About";
import Signup from "./pages/Signup";
import Login from "./pages/Login";
import Profile from "./pages/Profile";
import UpdatePage from "./pages/UpdatePage";
import BookingPage from "./pages/BookingPage";
import Ticket from "./pages/Ticket";

function App() {
  return (
    <div>
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}

      <BrowserRouter>
              <Routes>
                  <Route exact path="/" element={<Homepage/>} />
                  <Route exact path="/search" element={<Trainlist/>} />
                   <Route exact path="/about" element={<About/>} /> 
                   <Route exact path="/Signup" element={<Signup/>} />
                   <Route exact path="/Login" element={<Login/>} />
                   <Route exact path="/Profile" element={<Profile/>} />
                   <Route exact path="/update" element={<UpdatePage/>} />
                   <Route exact path="/book-ticket/fillForm/:train_id/:train_class/:date/:seats" element={<BookingPage/>} />
                   <Route exact path='/final-ticket' element={<Ticket/>}/>
                  {/* <Route exact path="/contact" element={<Contact/>} /> */}
              </Routes>
          </BrowserRouter>
    </div>
  );
}

export default App;
