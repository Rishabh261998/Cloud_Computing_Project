// LandingPage.js
import React, {useEffect} from 'react';
import { Link, useNavigate } from 'react-router-dom';

import '../App.css'; // Import your CSS file for styling

export default function LandingPage() {
  const navigate = useNavigate();

  useEffect(() => {
      if (localStorage.getItem('currentUser') != null) {
          navigate('/home');
          setTimeout(() => {
          alert('You are already Logged In!');
          }, 0);
      }
  }, [navigate]);
  return (
    <header className="landing-page-header">
      <div className="landing-page-content">
        <h1 className="text-center">Welcome! This is our Cloud-based E-commerce Website.</h1>
        <p className="text-center">Please Login before you start Shopping</p>
        <div className="buttons text-center">
          <Link to="/login">
            <button className="primary-button">Log In</button>
          </Link>
          <Link to="/register">
            <button className="primary-button" id="reg_btn">
              <span>Register </span>
            </button>
          </Link>
        </div>
      </div>
    </header>
  );
}
