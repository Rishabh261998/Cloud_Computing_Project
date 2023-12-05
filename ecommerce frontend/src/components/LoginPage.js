import React, {useState, useEffect} from 'react'
import { Link, useNavigate} from 'react-router-dom'
import axios from "axios"
import { API_URL } from './Constants'
// import { useSignIn } from 'react-auth-kit'

import '../App.css'

export default function SignInPage() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    // const signin = useSignIn();

    useEffect(() => {
        if (localStorage.getItem('currentUser') != null) {
            navigate('/home');
            setTimeout(() => {
            alert('You are already Logged In!');
            }, 0);
        }
    }, [navigate]);


    async function login(event) {
        event.preventDefault();

        try {
          await axios.post(API_URL.concat('/login'), {
            username: username,
            password: password,
            }).then((res) => 
            {
            //  console.log(res.data);
             localStorage.setItem('currentUser', JSON.stringify(res.data));
            //  signin({
            //     token: res.data.primaryKey,
            //     expiresIn: 3600,
            //     authState: {email: res.data.email}
            //  });
            });
             navigate("/home");
        }
         catch (err) {
          alert(err["response"].data);
        }
      
      }


    return (
        <div className="text-center m-5-auto">
            <h2>Sign in!</h2>
            <form action="/home">
                <p>
                    <label>Username</label><br/>
                    <input type="text" name="first_name" required onChange={(event) => {setUsername(event.target.value)}}/>
                </p>
                <p>
                    <label>Password</label>
                    <br/>
                    <input type="password" name="password" required onChange={(event) => {setPassword(event.target.value)}}/>
                </p>
                <p>
                    <button id="sub_btn" type="submit" onClick={login}>Login</button>
                </p>
            </form>
            <footer>
                <p>First time? <Link to="/register">Create an account</Link>.</p>
                <p><Link to="/">Back to Homepage</Link>.</p>
            </footer>
        </div>
    )
}