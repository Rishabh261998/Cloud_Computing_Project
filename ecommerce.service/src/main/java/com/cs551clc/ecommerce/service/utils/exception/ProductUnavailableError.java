package com.cs551clc.ecommerce.service.utils.exception;

import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ProductUnavailableError {
    private String message;
    private final List<ErrorProduct> errorProducts;

    public ProductUnavailableError(ProductUnavailableException e) {
        errorProducts = new ArrayList<>();
        message = e.getMessage();
        addErrors(e.getProductMap());
    }

    private void addErrors(Map<String, BigInteger> errors) {
        for(String productId: errors.keySet()) {
            errorProducts.add(new ErrorProduct(productId, errors.get(productId)));
        }
    }
}
