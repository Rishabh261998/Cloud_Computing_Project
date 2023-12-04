package com.cs551clc.ecommerce.service.utils.exception;

import java.math.BigInteger;
import java.util.Map;

public class ProductUnavailableException extends Exception{
    private final Map<String, BigInteger> productMap;

    @Override
    public String getMessage() {
        return "Following products have unsatisfiable quantities"+productMap;
    }

    public ProductUnavailableException(Map<String, BigInteger> productMap) {
        super();
        this.productMap=productMap;
    }
}
