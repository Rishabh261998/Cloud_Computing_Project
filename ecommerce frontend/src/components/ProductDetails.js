// ProductDetails.js
import React, { useState, useEffect } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { API_URL } from './Constants'

import '../App.css'; // Import your CSS file for styling

function ProductDetails({ addToCart }) {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem('currentUser') == null) {
      navigate('/login');
      setTimeout(() => {
        alert('You are not Logged In!');
      }, 0);
    }

    const data = fetch(API_URL.concat('/inventory'))
      .then((res) => res.json())
      .then((data) => {
        setProducts(data);
      });
  }, [navigate]);

  const { primaryKey } = useParams();
  const product = products.find((p) => p.primaryKey === primaryKey);

  if (!product) {
    return <div>Product not found</div>;
  }

  return (
    <div className="product-details-container">
      <h2>{product.sortKey}</h2>
      <p className="product-price">Price: ${product.price.toFixed(2)}</p>
      <button onClick={() => addToCart(product)} className="add-to-cart-button">
        Add to Cart
      </button>
      <span> </span>
      <Link to="/home">
        <button className="back-to-products-button">Back to Products Page</button>
      </Link>
    </div>
  );
}

export default ProductDetails;
