import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import axios from "axios"

import '../App.css'

export default function SignUpPage() {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");


    async function save(event) {
        event.preventDefault();
        try {
          await axios.put("http://localhost:8080/register", {
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
            <h2>Join us</h2>
            <h5>Create your personal account</h5>
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