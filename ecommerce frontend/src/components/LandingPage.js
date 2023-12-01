import React from 'react'
import { Link } from 'react-router-dom'

import '../App.css'

export default function LandingPage() {
    return (
        <header>
            <h1 className="text-center">Welcome! This is our Cloud based Ecommerce Website.</h1>
            <p className="text-center">Please Login before you start Shopping</p>
            <div className="buttons text-center">
                <Link to="/login">
                    <button className="primary-button">log in</button>
                </Link>
                <Link to="/register">
                    <button className="primary-button" id="reg_btn"><span>register </span></button>
                </Link>
            </div>
        </header>
    )
}