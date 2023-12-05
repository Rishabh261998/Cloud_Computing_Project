// ProductList.js
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { API_URL } from './Constants'

import '../App.css'; // Import your CSS file for styling

function ProductItem({ product, addToCart }) {
  return (
    <li key={product.primaryKey} className="product-li">
      <Link to={`/products/${product.primaryKey}`} className="product-link">
        <div className="product-total">
          <div className="product-image">
            <img src={product.img_link}/>
          </div>
          <div className="product-info">
            <div className="product-name">{product.sortKey}</div>
            <div className="product-price-quantity">
              <div className="product-price">${product.price.toFixed(2)}</div>
              <div className="product-quantity">Qty: {product.qty_available}</div>
            </div>
          </div>
        </div>
      </Link>
      <button onClick={() => addToCart(product)} className="add-to-cart-button">
        Add to Cart
      </button>
    </li>
  );
}

function groupProductsByCategory(products) {
  const groupedProducts = {};
  products.forEach((product) => {
    const category = product.category;
    if (!groupedProducts[category]) {
      groupedProducts[category] = [];
    }
    groupedProducts[category].push(product);
  });
  return groupedProducts;
}

function ProductList({ addToCart }) {
  const [allProducts, setallProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredProducts, setFilteredProducts] = useState(allProducts);
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem('currentUser') == null) {
      navigate('/login');
      setTimeout(() => {
        alert('You are not Logged In!');
      }, 0);
    } else {
      const data = fetch(API_URL.concat('/inventory'))
        .then((res) => res.json())
        .then((data) => {
          setallProducts(data);
          setFilteredProducts(data);
        });
    }
  }, [navigate]);

  const handleSearch = (event) => {
    const term = event.target.value.toLowerCase();
    setSearchTerm(term);

    const filtered = allProducts.filter((product) =>
      product.sortKey.toLowerCase().includes(term)
    );
    setFilteredProducts(filtered);
  };

  const groupedProducts = groupProductsByCategory(filteredProducts);

  return (
    <div className="product-list-container">
      <h2>Product List</h2>
      <input
        type="text"
        placeholder="Search products..."
        value={searchTerm}
        onChange={handleSearch}
        className="search-input"
      />
      <ul className="product-ul">
        {Object.entries(groupedProducts).map(([category, products]) => (
          <div key={category}>
            <h3 className="category-header">{category}</h3>
            <ul className="product-ul">
              {products.map((product) => (
                <ProductItem key={product.primaryKey} product={product} addToCart={addToCart} />
              ))}
            </ul>
          </div>
        ))}
      </ul>
      <Link to="/cart">
        <button className="go-to-cart-button">Go to Cart</button>
      </Link>
      <Link to="/orders">
        <button className="go-to-orders-button">Previous Orders</button>
      </Link>
    </div>
  );
}

export default ProductList;

