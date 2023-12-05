// OrdersPage.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom'
import { API_URL } from './Constants'

import '../App.css'

function OrdersPage() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem('currentUser') == null) {
        navigate('/login');
        setTimeout(() => {
          alert('You are not Logged In!');
        }, 0);
    }
    else {
        const curr = JSON.parse(localStorage.getItem('currentUser'));
        console.log(API_URL.concat(`/orders?userId=${curr.username}`));
        axios.get(API_URL.concat(`/orders?userId=${curr.username}`))
      .then((response) => {
        setOrders(response.data);
        setLoading(false);
      })
      .catch((error) => {
        // setError(error.response?.data || 'An error occurred while fetching orders.');
        setOrders([]);
        setLoading(false);
      });
    }
  }, [navigate]);

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error}</p>;
  }

  return (
    <div className="orders-container">
      <h2>Orders</h2>
      {orders.length === 0 ? (
        <p>No orders found.</p>
      ) : (
        <ul className="orders-ul">
          {orders.map((order) => (
            <li key={order.orderId} className="order-li">
              <div className="order-info">
                <p>Order ID: {order.orderId}</p>
                <p>Order Date: {order.dateTime}</p>
                <p>Products:</p>
                <ul>
                  {order.productIdList.map((productId, index) => (
                    <li key={productId}>
                      <p>{productId} - Quantity: {order.productQtyList[index]}</p>
                    </li>
                  ))}
                </ul>
              </div>
            </li>
          ))}
        </ul>
      )}
       <Link to="/home">
        <button className="back-to-products-button">Back to Products Page</button>
      </Link>
    </div>
  );
}

export default OrdersPage;