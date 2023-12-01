// App.js
import React, { useState, useEffect} from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';
import ProductList from './components/ProductList';
import ProductDetails from './components/ProductDetails';
import ShoppingCart from './components/ShoppingCart';
import LandingPage from './components/LandingPage'
import LoginPage from './components/LoginPage'
import RegisterPage from './components/RegisterPage'

import './App.css'

function App() {
  const [cartItems, setCartItems] = useState(JSON.parse(localStorage.getItem('shoppingCart')) || []);
  const navigate = useNavigate();

  const addToCart = (product) => {
    setCartItems([...cartItems, product]);
  };

  const signOut = () => {
    localStorage.clear('currentUser');
    localStorage.clear('shoppingCart');
    setCartItems([]);
    navigate('/');
    setTimeout(() => {
      alert("You are Logged Out!");
    }, 0);
  };

  const removeFromCart = (index) => {
    const newCartItems = [...cartItems];
    newCartItems.splice(index, 1);
    setCartItems(newCartItems);
  };

  useEffect(() => {
    localStorage.setItem('shoppingCart', JSON.stringify(cartItems));
  }, [cartItems]);

  const shouldLogoutButton = localStorage.getItem('currentUser') != null;
  const currentUser = JSON.parse(localStorage.getItem('currentUser'));
  // console.log(currentUser);

  return (
      <div>
          {shouldLogoutButton && (<button className="fancy-button" onClick = {signOut}>Logout</button>)}
            <Routes>
              <Route exact path="/" element={<LandingPage/> } />
              <Route path="/login" element={ <LoginPage/> } />
              <Route path="/register" element={ <RegisterPage/> } />
              <Route path="/home" element={<ProductList addToCart={addToCart}/>}/>
              <Route path="/products/:primaryKey" element={<ProductDetails addToCart={addToCart}/>}/>
              <Route path="/cart" element={<ShoppingCart cartItems={cartItems} removeFromCart={removeFromCart}/>}/>
            </Routes>
      </div>
  );
}

export default App;
