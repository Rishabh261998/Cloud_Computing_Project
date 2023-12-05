import React, { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { API_URL } from './Constants'
import axios from "axios"

import '../App.css'

export default function SignUpPage() {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");

    const navigate = useNavigate();

    useEffect(() => {
        if (localStorage.getItem('currentUser') != null) {
            navigate('/home');
            setTimeout(() => {
            alert('You are already Logged In!');
            }, 0);
        }
    }, [navigate]);


    async function save(event) {
        event.preventDefault();
        try {
          await axios.put(API_URL.concat('/register'), {
          full_name: name,
          email: email,
          password: password,
          username: username
          });
          alert("Employee Registation Successfully");
        } catch (err) {
          alert(err["response"].data);
        }
      }

    return (
        <div className="text-center m-5-auto">
            <h2>Register</h2>
            <form action="/home">
                <p>
                    <label>Full Name</label><br/>
                    <input type="text" name="first_name" required onChange= {(event) => {setName(event.target.value);}}/>
                </p>
                <p>
                    <label>Username</label><br/>
                    <input type="text" name="username" required onChange= {(event) => {setUsername(event.target.value);}}/>
                </p>
                <p>
                    <label>Email address</label><br/>
                    <input type="email" name="email" required onChange= {(event) => {setEmail(event.target.value);}}/>
                </p>
                <p>
                    <label>Password</label><br/>
                    <input type="password" name="password" required onChange= {(event) => {setPassword(event.target.value);}}/>
                </p>
                <p>
                    <button id="sub_btn" type="submit" onClick={save}>Register</button>
                </p>
            </form>
            <footer>
                <p><Link to="/">Back to Homepage</Link>.</p>
            </footer>
        </div>
    )

}