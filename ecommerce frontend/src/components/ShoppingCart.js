// ShoppingCart.js
import React, { useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function ShoppingCart({ cartItems, removeFromCart}) {

    const navigate = useNavigate();

    useEffect(() => {
        if(localStorage.getItem('currentUser') == null) {
            navigate('/login');
            setTimeout(() => {
              alert("You are not Logged In!");
            }, 0);
          }
    }, [navigate]);

    const handleRemove = (index) => {
        removeFromCart(index);
      };
    return (
    <div>
        <h2>Shopping Cart</h2>
        <ul>
        {cartItems.map((item, index) => (
            <li key={index}>
            {item.sortKey} - ${item.price}
            <button onClick={() => handleRemove(index)}>Remove</button>
            </li>
        ))}
        </ul>
        <Link to="/home">
        <button>Back to Products Page</button>
        </Link>
    </div>
    );
}

export default ShoppingCart;
