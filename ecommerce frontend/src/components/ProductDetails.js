// ProductDetails.js
import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';

function ProductDetails({ addToCart }) {
  const [products, setproducts] = useState([]);
  useEffect(() => {
    const data = fetch('http://localhost:8080/inventory')
               .then(res => res.json())
               .then(data => {
                setproducts(data);
               });
  }, []);
  const { primaryKey } = useParams();
  const product = products.find((p) => p.primaryKey === primaryKey);

  if (!product) {
    return <div>Product not found</div>;
  }

  return (
    <div>
      <h2>{product.sortKey}</h2>
      <p>Price: ${product.price}</p>
      <button onClick={() => addToCart(product)}>Add to Cart</button>
      <span> </span>
      <Link to="/">
        <button>Back to Products Page</button>
      </Link>
    </div>
  );
}

export default ProductDetails;
