package com.cs551clc.ecommerce.service.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    String username;
    List<String> productIdList;
    List<BigInteger> productQtyList;
}
