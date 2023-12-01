// Order.js
import React from 'react';
import { Link } from 'react-router-dom';

import '../App.css'; // Import your CSS file for styling

function Order() {
  return (
    <div className="order-container">
      <h2>Order Confirmation</h2>
      <p>Your order has been successfully placed! Thank you for shopping with us.</p>
      <Link to="/home">
        <button className="back-to-products-button">Back to Products Page</button>
      </Link>
    </div>
  );
}

export default Order;
