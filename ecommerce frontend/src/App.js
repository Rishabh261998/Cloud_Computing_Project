// App.js
import React, { useState, useEffect} from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ProductList from './components/ProductList';
import ProductDetails from './components/ProductDetails';
import ShoppingCart from './components/ShoppingCart';

function App() {
  const [cartItems, setCartItems] = useState(JSON.parse(localStorage.getItem('shoppingCart')) || []);

  const addToCart = (product) => {
    setCartItems([...cartItems, product]);
  };

  const removeFromCart = (index) => {
    const newCartItems = [...cartItems];
    newCartItems.splice(index, 1);
    setCartItems(newCartItems);
  };

  useEffect(() => {
    localStorage.setItem('shoppingCart', JSON.stringify(cartItems));
  }, [cartItems]);

  return (
    <Router>
      <div>
        <Routes>
          <Route exact path="/" element={<ProductList addToCart={addToCart} />} />
          <Route path="/products/:primaryKey" element={<ProductDetails addToCart={addToCart} />} />
          <Route path="/cart" element={<ShoppingCart cartItems={cartItems} removeFromCart={removeFromCart}/>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
