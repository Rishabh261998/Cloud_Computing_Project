// ShoppingCart.js
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { API_URL } from './Constants'
import axios from "axios"

import '../App.css';

function ShoppingCart({ cartItems, removeFromCart, addToCart, setCartItems }) {
  const navigate = useNavigate();
  const [totalCost, setTotalCost] = useState(0);
  const [isCartEmpty, setIsCartEmpty] = useState(true);

  useEffect(() => {
    if (localStorage.getItem('currentUser') == null) {
      navigate('/login');
      setTimeout(() => {
        alert('You are not Logged In!');
      }, 0);
    }
  }, [navigate]);

  useEffect(() => {
    // Check if the cart is empty
    setIsCartEmpty(cartItems.length === 0);
  }, [cartItems]);

  const handleRemove = (sortKey) => {
    const indexToRemove = cartItems.findIndex((item) => item.sortKey === sortKey);
    removeFromCart(indexToRemove);
  };

  const handleAdd = (sortKey) => {
    // Implement the logic to add the item to the cart
    // This could involve updating the quantity or adding a new item to the cart
    // For simplicity, let's assume adding the same item for now
    const indexToAdd = cartItems.findIndex((item) => item.sortKey === sortKey);
    // Assuming you have a function like addToCart that adds an item to the cart
    // You need to implement this function based on your application's state management
    addToCart(cartItems[indexToAdd]);
  };

  const handleOrder = () => {
    // Check if the cart is empty before placing an order
    if (isCartEmpty) {
      alert('Your cart is empty. Add items before placing an order.');
    } else {
      // Implement your order placement logic here

      const orderData = {
        username: JSON.parse(localStorage.getItem('currentUser')).username, // Assuming you store the username in localStorage
        productIdList: [],
        productQtyList: [],
      };
  
      // Calculate quantity for each product
      const productQuantities = {};
      cartItems.forEach((item) => {
        const productId = item.primaryKey;
        productQuantities[productId] = (productQuantities[productId] || 0) + 1;
      });
  
      // Populate productIdList and productQtyList
      Object.entries(productQuantities).forEach(([productId, quantity]) => {
        orderData.productIdList.push(productId);
        orderData.productQtyList.push(quantity);
      });
      console.log(orderData);
      try {
        axios.put(API_URL.concat('/order'), orderData)
        .then((res) => {
          navigate('/order')
          // Remove items from the cart after placing the order
          clearCart();
        })
        .catch((error) => {
          alert('Order placement failed:' + error.response.data.message);
          navigate('/home');
        });
      } catch (error) {
        console.log(error);
        navigate('/home');
      }
    }
  };

  const clearCart = () => {
    // Clear the cart items using setCartItems
    setCartItems([]);
  };

  // Group items by sortKey and calculate quantity and total cost
  const groupedCartItems = cartItems.reduce((acc, item) => {
    const existingItem = acc.find((groupedItem) => groupedItem.sortKey === item.sortKey);
    if (existingItem) {
      existingItem.quantity += 1;
      existingItem.totalCost += item.price;
    } else {
      acc.push({ ...item, quantity: 1, totalCost: item.price });
    }
    return acc;
  }, []);

  // Calculate overall total cost
  useEffect(() => {
    const total = groupedCartItems.reduce((acc, item) => acc + item.totalCost, 0);
    setTotalCost(total);
  }, [groupedCartItems]);

  return (
    <div className="shopping-cart-container">
      <h2>Shopping Cart</h2>
      {isCartEmpty ? (
        <p>Your cart is empty. Add items before placing an order.</p>
      ) : (
        <>
          <ul className="cart-ul">
            {groupedCartItems.map((item, index) => (
              <li key={index} className="cart-li">
                <div className="cart-item-info">
                  <div className="cart-item-name">{item.sortKey}</div>
                  <div className="cart-item-price">${item.price.toFixed(2)}</div>
                </div>
                <div className="cart-item-quantity">Quantity: {item.quantity}</div>
                <div className="cart-item-total-cost">Total Cost: ${item.totalCost.toFixed(2)}</div>
                <div className="cart-item-actions">
                  <button onClick={() => handleAdd(item.sortKey)} className="add-button">
                    Add
                  </button>
                  <button onClick={() => handleRemove(item.sortKey)} className="remove-button">
                    Remove
                  </button>
                </div>
              </li>
            ))}
          </ul>
          <div className="overall-total-cost">Overall Total Cost: ${totalCost.toFixed(2)}</div>
          <button onClick={handleOrder} className="order-button">
            Place Order
          </button>
        </>
      )}
      <Link to="/home">
        <button className="back-to-products-button">Back to Products Page</button>
      </Link>
    </div>
  );
}

export default ShoppingCart;
