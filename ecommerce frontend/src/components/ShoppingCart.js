// ShoppingCart.js
import React from 'react';
import { Link } from 'react-router-dom';

function ShoppingCart({ cartItems, removeFromCart}) {

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
        <Link to="/">
        <button>Back to Products Page</button>
        </Link>
    </div>
    );
}

export default ShoppingCart;
