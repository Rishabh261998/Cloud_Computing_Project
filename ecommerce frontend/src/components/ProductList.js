// ProductList.js
import React, {useEffect, useState} from 'react';
import { Link } from 'react-router-dom';

// const allProducts = [
//   { id: 1, name: 'Product 1', price: 19.99 },
//   { id: 2, name: 'Product 2', price: 29.99 },
//   // Add more products as needed
// ];



function ProductList({ addToCart }) {
    const [allProducts, setallProducts] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [filteredProducts, setFilteredProducts] = useState(allProducts);
    useEffect(() => {
      const data = fetch('http://localhost:8080/inventory')
                 .then(res => res.json())
                 .then(data => {
                  setallProducts(data);
                  setFilteredProducts(data);
                 });
    }, []);
  
    const handleSearch = (event) => {
      const term = event.target.value.toLowerCase();
      setSearchTerm(term);
  
      const filtered = allProducts.filter((product) =>
        product.sortKey.toLowerCase().includes(term)
      );
      setFilteredProducts(filtered);
    };
    return (
    <div>
        <h2>Product List</h2>
        <input
        type="text"
        placeholder="Search products..."
        value={searchTerm}
        onChange={handleSearch}
        />
        <ul>
        {filteredProducts.map((product) => (
            <li key={product.primaryKey}>
            <Link to={`/products/${product.primaryKey}`}>
                {product.sortKey} - ${product.price}
            </Link>
            <button onClick={() => addToCart(product)}>Add to Cart</button>
            </li>
        ))}
        </ul>
        <Link to="/cart">
        <button>Go to Cart</button>
        </Link>
    </div>
    );
}

export default ProductList;
